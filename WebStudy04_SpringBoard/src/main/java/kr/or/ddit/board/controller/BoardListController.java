package kr.or.ddit.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PagingVO;
import kr.or.ddit.board.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;

/**
 * HandlerAdapter 사용방법(컨트롤러 구현방법)
 * 1. @Controller : 컨테이너에 빈으로 등록되며, HandlerMapping 수집되는 컨트롤러 객체임.
 * 2. @RequestMapping (method-level) : 컨트롤러가 처리 가능한 요청을 제한(mapping)함.
 * 		- value : request URI
 * 		- method : http method
 * 		- params : request parameter
 * 		- headers : request header
 * 		- consume : request Content-Type
 * 		- produce : reqeust Accept / response Content-Type
 * 3. 요청과 관련된 모든 데이터는 HandlerAdapter로부터 받되, 핸들러 메소드 파라미터 형태로.
 * 		1)@RequestXXX : RequestParam, RequestHeader, RequestPart
 * 		2)@ModelAttribute : 요청 파라미터를 VO 객체로 받을 때, 특정(request) scope를 통해 모델 속성을 공유하게됨.
 * 		3)@RequestBody : request body(message, content)를 Command Object를 이용해 받을 때.
 * 				@RequestMapping(consumes, unmarshalling)
 * 		4)@ResponseBody : 핸들러 메서드의 리턴값으로 response body(message, content)를 구성할 떄.
 * 				@RequestMapping(produces, marshalling)
 *
 */
@Slf4j
@Controller
public class BoardListController {
	private BoardService service;
	
	@Inject
	public void setService(BoardService service) {
		this.service = service;
		log.info("주입된 business logic : {}", service.getClass().getName());
	}
	
	@RequestMapping(value="board/boardList.do", method=RequestMethod.GET)
	public String listUI() {
		return "board/boardList";
	}
	
	@RequestMapping(value="board/boardList.do", method=RequestMethod.GET
					, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<BoardVO> list(
		@RequestParam(name="page", required=false, defaultValue="1") int currentPage
		, @ModelAttribute("simpleCondition") SearchVO simpleCondition
//		, Model model
	) {
		
		PagingVO<BoardVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSimpleCondition(simpleCondition);
		int totalRecord = service.retirveBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<BoardVO> boardList = service.retriveBoardList(pagingVO);
		pagingVO.setDataList(boardList);
		
		return pagingVO;
//		model.addAttribute("pagingVO", pagingVO);
//		return "board/boardList";
	}
	
	@RequestMapping("board/boardView.do")
	public ModelAndView boardView(@RequestParam(name="what", required=true) int boNo) {
		BoardVO board = service.retriveBoard(boNo);
		ModelAndView mav = new ModelAndView();
		mav.addObject("board", board);
		mav.setViewName("board/boardView");
		return mav;
	}
}