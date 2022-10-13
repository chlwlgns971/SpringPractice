package kr.or.ddit.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.BoardDAO;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {
	private BoardDAO boardDAO;

	@Inject
	public BoardServiceImpl(BoardDAO boardDAO) {
		super();
		this.boardDAO = boardDAO;
		log.info("주입된 객체 : {}", boardDAO);
	}

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

	@Override
	public int createBoard(BoardVO board) {
		return boardDAO.insertBoard(board);
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

	@Override
	public ServiceResult removeBoard(BoardVO board) {
		ServiceResult result = null;
		boolean check = boardAuthenticate(board);
		if(check) {
			int cnt = boardDAO.deleteBoard(board);
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
}
