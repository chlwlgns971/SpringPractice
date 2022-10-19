package kr.or.ddit.prod.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

/**
 * /prod/prodList.do(GET)  
 * /prod/prodView.do?what=P101000001(GET)
 * /prod/prodInsert.do(GET)
 * /prod/prodInsert.do(POST)
 * /prod/prodUpdate.do?what=P101000001(GET)
 * /prod/prodUpdate.do(POST)
 */
@Controller
@RequestMapping("/prod")
public class ProdListController{
	@Inject
	ProdService service;
	
	@RequestMapping("prodList.do")
	private String processHTML(){
		return "prod/prodList";
	}
	@RequestMapping(value="prodList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String processJsonData(
			ProdVO detailCondition
			, @RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, Model model
	){
		PagingVO<ProdVO> pagingVO = new PagingVO<>(5,2);
		pagingVO.setCurrentPage(currentPage);
		//pagingVO.setSimpleCondition(simpleCondition);
		pagingVO.setDetailCondition(detailCondition);
		
		int totalRecord = service.retrieveProdCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<ProdVO> prodList = service.retrieveProdList(pagingVO);
		pagingVO.setDataList(prodList);

		model.addAttribute("pagingVO", pagingVO);
		return "jsonView";
		
	}
}
