package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PagingVO;

/**
 * 게시판관리(CRUD)용 Persistence Layer 
 *
 */
@Mapper
public interface BoardDAO {
	public BoardVO selectBoard(@Param("boNo") int boNo);
	public int incrementBoHit(@Param("boNo") int boNo);
	public int incrementBoNo();
	public List<BoardVO> selectBoardList(PagingVO<BoardVO> pageVo);
	public int selectTotalRecord(PagingVO<BoardVO> pageVo);
	public int insertBoard(BoardVO board);
	public int updateBoard(BoardVO board);
	public int deleteBoard(BoardVO board);
}
