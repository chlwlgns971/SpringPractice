package kr.or.ddit.commons.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index.do")
public class IndexServlet {
	@RequestMapping
	protected String index(){
		return "index";
	}
}
