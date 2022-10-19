package kr.or.ddit.member.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.ServiceResult;
import kr.or.ddit.vo.MemberVO;

//@WebServlet("/member/memberDelete.do")
@Controller
@RequestMapping("/member/memberDelete.do")
public class MemberDeleteServlet{
	@Inject
	MemberService service;
	
	public String doPost(@RequestParam(name="who") String id, RedirectAttributes redirectAttributes){
		MemberVO member = new MemberVO();
		member.setMemId(id);
		
		ServiceResult result = service.removeMember(member);
		String commandPage = null;
		if(ServiceResult.OK.equals(result)) {
			commandPage = "redirect:/member/memberList.do";
		}else {
			redirectAttributes.addFlashAttribute("message", "삭제처리 실패");
			commandPage = "redirect:/member/memberList.do";
		}
		return commandPage;
	}
}
