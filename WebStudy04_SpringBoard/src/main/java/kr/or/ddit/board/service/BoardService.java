package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PagingVO;

/**
 * 게시판 관리용 Business Logic Layer 
 *
 */
public interface BoardService {
	public int createBoard(BoardVO board);
	public BoardVO retriveBoard(int boNo);
	public List<BoardVO> retriveBoardList(PagingVO<BoardVO> pageVo);
	public int retirveBoardCount(PagingVO<BoardVO> pageVo);
//	modifyBoard
//	removeBoard
}
