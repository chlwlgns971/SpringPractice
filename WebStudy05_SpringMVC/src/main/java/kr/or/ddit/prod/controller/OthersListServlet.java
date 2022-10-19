package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.vo.BuyerVO;

@Controller
@RequestMapping("/prod")
public class OthersListServlet{
	@Inject
	private OthersDAO othersDAO;
	
	@RequestMapping("/getLprodList.do")
	public String processLprodList(Model model){
		List<Map<String, Object>> lprodList = othersDAO.selectLprodList();
		model.addAttribute("model", lprodList);
		return "jsonView";
	}
	@RequestMapping("/getBuyerList.do")
	public String processBuyerList(String lprodGu, Model model){
		List<BuyerVO> buyerList = othersDAO.selectBuyerList(lprodGu);
		model.addAttribute("model", buyerList);
		return "jsonView";
	}
}
