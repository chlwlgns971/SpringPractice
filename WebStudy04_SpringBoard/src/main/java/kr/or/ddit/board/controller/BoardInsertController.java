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
			int check = service.createBoard(board);
			
			if(check > 0) {
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
