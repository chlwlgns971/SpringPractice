package kr.or.ddit.board.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

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
			if(cnt > 0) {
				result = ServiceResult.OK;
			}else result = ServiceResult.FAIL;
		}
		return result;
	}
	
	private int processDeleteAttatches(BoardVO board) {
		int rowcnt = 0;
		List<AttatchVO> attatchList = boardDAO.selectBoard(board.getBoNo()).getAttatchList();
		List<String> saveNames = attatchList.stream().map(attatch->{
			return attatch.getAttSavename();
		}).collect(Collectors.toList());
		
		//메타데이터 삭제
		rowcnt = attatchDAO.deleteAttatches(board.getBoNo());
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
}
