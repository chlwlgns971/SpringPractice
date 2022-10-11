package kr.or.ddit.board.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/*-context.xml")
public class BoardDAOTest {
	@Inject
	private BoardDAO dao;

	@Test
	public void testSelectBoard() {
		BoardVO board = dao.selectBoard(2000);
		log.info("selectBoard result : {}", board);
	}

	@Test
	public void testSelectBoardList() {
		PagingVO<BoardVO> vo = new PagingVO<>();
		vo.setCurrentPage(1);
		vo.setTotalRecord(1124);
		List<BoardVO> list = dao.selectBoardList(vo);
		log.info("selectBoardList result : {}", list);
	}

	@Test
	public void testSelectTotalRecord() {
		PagingVO<BoardVO> vo = new PagingVO<>();
		vo.setCurrentPage(1);
		int count = dao.selectTotalRecord(vo);
		log.info("selectTotalRecord result : {}", count);
	}

}
