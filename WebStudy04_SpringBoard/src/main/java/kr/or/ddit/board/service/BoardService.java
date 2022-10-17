package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.AttatchVO;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PagingVO;

/**
 * 게시판 관리용 Business Logic Layer 
 *
 */
public interface BoardService {
	public ServiceResult createBoard(BoardVO board);
	public BoardVO retriveBoard(int boNo);
	public List<BoardVO> retriveBoardList(PagingVO<BoardVO> pageVo);
	public int retirveBoardCount(PagingVO<BoardVO> pageVo);
	public int retirveBoardNoCount();
	/**
	 * 게시글 수정(인증필요)
	 * @param boNo
	 * @return RuntimeException, INVALIDPASSWORD, OK, FAIL
	 */
	public ServiceResult modifyBoard(BoardVO board);
	/**
	 * 게시글 수정(인증필요)
	 * @param boNo
	 * @return RuntimeException, INVALIDPASSWORD, OK, FAIL
	 */
	public ServiceResult removeBoard(BoardVO board);
	
	/**
	 * 
	 * @param boNo
	 * @return 갱신된 추천수
	 */
	public int recommend(int boNo);
	
	/**
	 * @param attNo
	 * @return 존재 여부에 따라 RuntimeException 발생
	 */
	public AttatchVO retrieveAttatch(int attNo);
}
