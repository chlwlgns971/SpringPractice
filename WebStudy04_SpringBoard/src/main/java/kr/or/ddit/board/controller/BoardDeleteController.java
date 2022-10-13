package kr.or.ddit.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.ServiceResult;
import kr.or.ddit.board.vo.BoardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board/boardDelete.do")
@Controller
public class BoardDeleteController {
	private final BoardService service;
	
	@PostMapping
	public String deleteBoard(BoardVO board, RedirectAttributes redirectAttributes) {
		ServiceResult result = service.removeBoard(board);
		log.info("result : {}",result);
		String viewName = "";
		if(result == ServiceResult.OK) {
			viewName = "redirect:/board/boardList.do";
		}else{
			String message = "삭제실패";
			redirectAttributes.addFlashAttribute("message",message);
			viewName = "redirect:/board/boardView.do?what="+board.getBoNo();
		}
		return viewName;
	}
}
