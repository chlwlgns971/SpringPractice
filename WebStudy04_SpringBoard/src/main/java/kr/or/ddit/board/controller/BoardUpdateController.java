package kr.or.ddit.board.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.ServiceResult;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.validate.UpdateGroup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board/boardUpdate.do")
@RequiredArgsConstructor
@Controller
public class BoardUpdateController {
	private final BoardService service;
	
	@GetMapping
	public ModelAndView boardForm(@RequestParam(name="what", required=true) int boNo) {
		BoardVO board = service.retriveBoard(boNo);
		ModelAndView mav = new ModelAndView();
		mav.addObject("board", board);
		mav.setViewName("board/boardEdit");
		return mav;
	}
	
	@PostMapping
	public String updateBoard(
		@Validated(UpdateGroup.class) @ModelAttribute("board") BoardVO board
		,BindingResult errors
		, Model model	
	) {
		String viewName = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyBoard(board);
			log.info("result = {}", result);
			
			if(result == ServiceResult.OK) {
				viewName = "redirect:/board/boardView.do?what="+board.getBoNo();
			}else{
				String message = "등록실패";
				model.addAttribute("message",message);
				viewName = "board/boardEdit";
			}
		}else {
			viewName = "board/boardEdit";
		}
		return viewName;
	}
}
