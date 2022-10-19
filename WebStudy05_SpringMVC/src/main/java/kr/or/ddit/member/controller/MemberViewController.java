package kr.or.ddit.member.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;

//@WebServlet("/member/memberView.do")
@Controller
@RequestMapping("/member/memberView.do")
public class MemberViewController{
	@Inject
	private MemberService service;
	
	@RequestMapping
	public String viewMember(@RequestParam(name="who", required=true) String memId, Model model){      
		MemberVO member = service.retrieveMember(memId);
      
		model.addAttribute("member", member);
      
      
		return "member/memberView";
	}
}
      
