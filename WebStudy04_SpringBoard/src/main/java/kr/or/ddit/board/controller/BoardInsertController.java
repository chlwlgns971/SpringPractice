package kr.or.ddit.board.controller;
import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.board.event.NewBoardEvent;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.ServiceResult;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.validate.InsertGroup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/board/boardInsert.do")
public class BoardInsertController {
	private final BoardService service;
	@Inject
	private WebApplicationContext context;
	
	@ModelAttribute("board")
	public BoardVO board() {
		log.info("@ModelAttribute 메서드 실행.");
		return new BoardVO();
	}
	
	@GetMapping	
	public String boardForm(@ModelAttribute("board") BoardVO board) {
		log.info("Get 메서드 핸들러 boardForm 실행");
		return "board/boardForm";
	}
	
	/**
	 * 만약 Controller에서 받는 객체가 여러개고 각각 유효성 검사를 해야할 경우 Errors 객체는 받는 객체만큼 존재해야한다.
	 * 하지만 Errors 위치가 섞여있으면 알기가 힘들기 때문에 Errors는 검증객체 뒤에 적어주는 것이 보편적이다.
	 */
	@PostMapping
	public String boardInsert(
			@Validated(InsertGroup.class) @ModelAttribute("board") BoardVO board
			,Errors errors
			, Model model
		) {
		log.info("Post 메서드 핸들러 boardInsert 실행");
		String viewName = null;
		if(!errors.hasErrors()) {
			//글쓰기가 완료되면 해당글 상세보기 페이지로 이동한다.
			board.setBoHit(0);
			board.setBoRec(0);
			ServiceResult result = service.createBoard(board);
			
			if(result == ServiceResult.OK) {
				viewName = "redirect:/board/boardView.do?what="+board.getBoNo();
				NewBoardEvent event = new NewBoardEvent(board);
				context.publishEvent(event);
				
			}else{
				String message = "등록실패";
				model.addAttribute("message",message);
				viewName = "board/boardForm";
			}
		}else {
			viewName = "board/boardForm";
		}
		return viewName;
		
	}
}
