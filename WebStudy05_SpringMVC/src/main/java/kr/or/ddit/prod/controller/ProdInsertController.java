package kr.or.ddit.prod.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.buyer.controller.BuyerListServlet;
import kr.or.ddit.file.filter.StandardMultipartHttpServletRequest;
import kr.or.ddit.member.service.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;

@Controller
@RequestMapping("/prod/prodInsert.do")
public class ProdInsertController{
	@Inject
	private ProdService service;
	
	@ModelAttribute("command")
	public String command() {
		return "INSERT";
	}
	
	@ModelAttribute("prod")
	public ProdVO prod() {
		return new ProdVO();
	}
	
	@GetMapping
	public String doGet() {
		return "prod/prodForm";
	}
	
	@PostMapping
	public String doPost(
			@Validated(InsertGroup.class) @ModelAttribute("prod") ProdVO prod
			, Errors errors
			, Model model
	){
		String logicalViewName = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.createProd(prod);
			switch (result) {
			case PKDUPLICATED:
				model.addAttribute("message", "아이디중복");
				logicalViewName = "prod/prodForm";	
				break;	
			case OK:
				logicalViewName = "redirect:/prod/prodList.do";
				break;

			default:
				model.addAttribute("message", "서버오류");
				logicalViewName = "prod/prodForm";
				break;
			}
		}else {
			logicalViewName = "prod/prodForm";
		}
		return logicalViewName;
	}
}
