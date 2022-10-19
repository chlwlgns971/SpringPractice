package kr.or.ddit.login.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.commons.exception.UserNotFoundException;
import kr.or.ddit.login.BadCredentialException;
import kr.or.ddit.login.service.AutheticationService;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;

//@WebServlet("/login/loginProcess.do")
@Controller
@RequiredArgsConstructor
public class LoginProcessController{
	private final AutheticationService service;
	@GetMapping("/login/loginForm.do")
	public String loginForm() {
		return "login/loginForm";
	}
	
	@PostMapping("/login/loginProcess.do")
	public String loginProcess(String memId, String memPass, HttpSession session, RedirectAttributes redirectAttributes){
				
		String viewName = null;
		String message = null;
		if(StringUtils.isBlank(memId) || StringUtils.isBlank(memPass)) {
			message = "아이디나 비밀번호 누락";
			viewName = "redirect:/login/loginForm.do";
		}else {
			MemberVO inputData = new MemberVO();
			inputData.setMemId(memId);
			inputData.setMemPass(memPass);
			try {
				MemberVO authMember = service.authenticate(inputData);
				session.setAttribute("authMember", authMember);
				
				viewName = "redirect:/";
				
			}catch (UserNotFoundException | BadCredentialException e) {
				if(e instanceof UserNotFoundException) {
					message = "해당 회원 없음.";
				}else {
					message = "비밀번호 오류임.";
				}
				viewName = "redirect:/login/loginForm.do";
			}
		}
		
		redirectAttributes.addFlashAttribute("message", message);
		return viewName;
	}
}
