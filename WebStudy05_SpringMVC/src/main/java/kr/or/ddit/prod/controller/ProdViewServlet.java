package kr.or.ddit.prod.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.vo.ProdVO;

@Controller
@RequestMapping("/prod/prodView.do")
public class ProdViewServlet{
	@Inject
	ProdService service;
	
	public String prodView(@RequestParam(name="what") String id, Model model){
		ProdVO prod = service.retrieveProd(id);
		model.addAttribute("prod", prod);
		return "prod/prodView";
	}
}
