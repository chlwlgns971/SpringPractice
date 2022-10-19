package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

/**
 * /member (GET) /member/a001 (GET) /member/a001 (PUT) /member/a001 (DELETE)
 * ->이런 형태의 URL를 RESTful URI라고 한다.
 * 
 * non-RESTful URI /member/memberList.do (GET) 
 * /member/memberView.do?who=a001 (GET) 
 * /member/memberInsert.do (GET) 
 * /member/memberInsert.do (POST)
 * /member/memberUpdate.do?who=a001 (GET) 
 * /member/memberUpdate.do?who=a001 (POST) 
 * /member/memberDelete.do (POST)
 */
//@WebServlet("/member/memberList.do")
@Controller
public class MemberListController{
	@Inject
	private MemberService service;
	
	@RequestMapping("/member/memberList.do")
	protected String memberList(
			String searchType
			, String searchWord
			, @RequestParam(name="page", defaultValue="1") String pageParam
			, Model model){
		SearchVO simpleCondition = new SearchVO(searchType, searchWord);
	    
		int currentPage = 1;
		if(StringUtils.isNumeric(pageParam)) {
			//여기들ㄹ어오면 안전하게 파싱 가능!
			currentPage = Integer.parseInt(pageParam);
		}
		PagingVO<MemberVO> pagingVO = new PagingVO<>(3,2);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSimpleCondition(simpleCondition);
		
		int totalRecod = service.retrieveMemberCount(pagingVO);
		pagingVO.setTotalRecord(totalRecod);
		
		List<MemberVO> memberList = service.retrieveMemberList(pagingVO);
		pagingVO.setDataList(memberList);
		
		model.addAttribute("memberList", memberList);
		model.addAttribute("pagingVO", pagingVO);

		return "member/memberList";
	}
}
