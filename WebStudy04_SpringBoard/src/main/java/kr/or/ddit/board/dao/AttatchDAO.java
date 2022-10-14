package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.board.vo.AttatchVO;
import kr.or.ddit.board.vo.BoardVO;

@Mapper
public interface AttatchDAO {
	/**
	 * 게시글의 첨부파일의 메타데이터를 한번에 insert
	 * @param board
	 * @return
	 */
	public int insertAttatches(BoardVO board);
	
	/**
	 * 다운로드 처리를 위해 메타데이터 한건 조회.
	 * @param attNo
	 * @return
	 */
	public AttatchVO selectAttatch(int attNo);
	
	/**
	 * 게시글 조회에서 첨부파일을 위한 해당 게시글 첨부파일 조회.
	 * @param boNo
	 * @return
	 */
	public List<AttatchVO> selectAttatches(int boNo);
	public int updateAttatch();
	
	/**
	 * 게시글 수정시 한건 한건의 파일의 메타데이터 삭제용.
	 * @param attNo
	 * @return
	 */
	public int deleteAttatch(int attNo);
	
	/**
	 * 게시글에 첨부된 모든 파일의 메타데이터 삭제용.
	 * @param boNo
	 * @return
	 */
	public int deleteAttatches(int boNo);
}
