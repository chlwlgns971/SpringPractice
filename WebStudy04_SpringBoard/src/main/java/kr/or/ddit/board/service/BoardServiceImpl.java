package kr.or.ddit.board.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.board.dao.AttatchDAO;
import kr.or.ddit.board.dao.BoardDAO;
import kr.or.ddit.board.vo.AttatchVO;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PagingVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
	private final BoardDAO boardDAO;
	private final AttatchDAO attatchDAO;

//	@Inject
//	public BoardServiceImpl(BoardDAO boardDAO) {
//		super();
//		this.boardDAO = boardDAO;
//		log.info("주입된 객체 : {}", boardDAO);
//	}

	@Override
	public BoardVO retriveBoard(int boNo) {
		BoardVO board = boardDAO.selectBoard(boNo);
		if(board == null) {
			throw new RuntimeException(String.format("%d 글번호의 글이 없음.", boNo));
		}
		boardDAO.incrementBoHit(boNo);
		return boardDAO.selectBoard(boNo);
	}

	@Override
	public List<BoardVO> retriveBoardList(PagingVO<BoardVO> pageVo) {
		return boardDAO.selectBoardList(pageVo);
	}

	@Override
	public int retirveBoardCount(PagingVO<BoardVO> pageVo) {
		return boardDAO.selectTotalRecord(pageVo);
	}

	@Value("#{appInfo.attatchFolder}")
	private Resource attatchFolder;
	
	private int processAttatchList(BoardVO board) {
		int rowcnt = 0;
		List<AttatchVO> attatchList = board.getAttatchList();
		if(attatchList!=null && !attatchList.isEmpty()) {
			//메타 데이터 저장
			rowcnt = attatchDAO.insertAttatches(board);
			
			//2진 데이터 저장
			for(AttatchVO attatch : attatchList) {
				try {
					attatch.saveTo(attatchFolder.getFile());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
		
		return rowcnt;
	}
	
	//@Transactional
	@Override
	public ServiceResult createBoard(BoardVO board) {
		int rowcnt = boardDAO.insertBoard(board);
		rowcnt +=processAttatchList(board);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public int retirveBoardNoCount() {
		return boardDAO.incrementBoNo();
	}

	@Override
	public ServiceResult modifyBoard(BoardVO board) {
		ServiceResult result = null;
		boolean check = boardAuthenticate(board);
		if(check) {
			int cnt = boardDAO.updateBoard(board);
			//1. 기존 파일 삭제
			if(board.getDelAttNos() != null) {
				cnt += processDeleteAttatches(board);
			}
			//2. 신규파일등록
			cnt += processAttatchList(board);
			if(cnt > 0) {
				result = ServiceResult.OK;
			}else result = ServiceResult.FAIL;
		}
		return result;
	}
	
//	private int processDeleteAttatch(BoardVO board) {
//		int[] delAttNos = board.getDelAttNos();
//		if(delAttNos==null || delAttNos.length == 0) return 0;
//		Arrays.sort(delAttNos);
// 		List<AttatchVO> attatchList = boardDAO.selectBoard(board.getBoNo()).getAttatchList();
//		List<String> saveNames = attatchList.stream()
//				.filter(attatch->{
//					return Arrays.binarySearch(delAttNos, attatch.getAttNo()) >= 0;
//				}).map(attatch->{
//					return attatch.getAttSavename();
//				}).collect(Collectors.toList());
//		int rowcnt = attatchDAO.deleteAttatch(delAttNos);
//		// 2진 데이터 삭제
//		if(!saveNames.isEmpty()) {
//			for(String attSavename : saveNames) {
//				File deleteFile = new File(saveFolder, attSavename);
//				FileUtils.deleteQuietly(deleteFile);
//			}
//		}
//		return rowcnt;
//	}
	
	private int processDeleteAttatches(BoardVO board) {
		int rowcnt = 0;
		List<AttatchVO> attatchList = new ArrayList<>();
		List<String> saveNames = new ArrayList<>();
		if(board.getDelAttNos() == null) {
			attatchList = boardDAO.selectBoard(board.getBoNo()).getAttatchList();
			//메타데이터 삭제
			rowcnt = attatchDAO.deleteAttatches(board.getBoNo());
		}
		else {
			log.info("board.getDelAttNos() : {}",board.getDelAttNos());
			for(int attNo : board.getDelAttNos()) {
				attatchList.add(attatchDAO.selectAttatch(attNo));
			}
			//메타데이터 삭제
			rowcnt = attatchDAO.deleteAttatch(board.getDelAttNos());
			
		}
		log.info("attatchList : {}",attatchList);
		if(rowcnt>0) {		
			saveNames = attatchList.stream().map(attatch->{
				return attatch.getAttSavename();
			}).collect(Collectors.toList());
			log.info("saveNames : {}",saveNames);
			
			//2진 데이터 삭제
			if(attatchList!=null && !attatchList.isEmpty()) {
				for(AttatchVO attatch : attatchList) {
					try {
						attatch.deleteTo(saveNames, attatchFolder.getFile());
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}
			}
		}
		
		return rowcnt;
	}

	@Override
	public ServiceResult removeBoard(BoardVO board) {
		ServiceResult result = null;
		boolean check = boardAuthenticate(board);
		if(check) {
			int cnt = processDeleteAttatches(board);
			cnt = boardDAO.deleteBoard(board);
			if(cnt > 0) {
				result = ServiceResult.OK;
			}else result = ServiceResult.FAIL;
		}
		return result;
	}
	
	private boolean boardAuthenticate(BoardVO board) {
		BoardVO saved = retriveBoard(board.getBoNo());
		String inputPass = board.getBoPass();
		String savedPass = saved.getBoPass();
		return savedPass.equals(inputPass);
	}

	@Override
	public int recommend(int boNo) {
		int recNum = 0;
		int rowcnt = boardDAO.incrementRec(boNo);
		if(rowcnt > 0) {
			BoardVO board = boardDAO.selectBoard(boNo);
			recNum = board.getBoRec();
		}
		return recNum;
	}

	@Override
	public AttatchVO retrieveAttatch(int attNo) {
		AttatchVO attatch = attatchDAO.selectAttatch(attNo);
		if(attatch == null) throw new RuntimeException("해당 파일 없음.");
		return attatch;
	}
}
