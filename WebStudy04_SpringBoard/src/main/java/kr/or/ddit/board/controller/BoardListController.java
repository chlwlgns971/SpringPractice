package kr.or.ddit.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PagingVO;

@Controller
public class BoardListController {
	@Inject
	private BoardService service;
	@RequestMapping(value="/board/boardList.do", method=RequestMethod.GET)
	public String list(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, Model model
	) {
		PagingVO<BoardVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		int totalRecord = service.retirveBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<BoardVO> boardList = service.retriveBoardList(pagingVO);
		pagingVO.setDataList(boardList);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "board/boardList";
	}
}
