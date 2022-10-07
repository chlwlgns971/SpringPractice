package kr.or.ddit.props.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.props.service.PropertyService;
import kr.or.ddit.props.service.PropertyServiceImpl;
import kr.or.ddit.props.vo.PropertyVO;

//@WebServlet({ "/properties", "/property" })
@Controller
public class PropertiesController{
	@Inject
	private PropertyService service;
	
	@RequestMapping("/properties")
	public List<PropertyVO> properties() {
		return service.readProperties();
	}
	
	@GetMapping("/property")
	protected PropertyVO doGet(@RequestParam(required=true) String name) throws IOException {
		return service.readProperty(name);
	}

	@PostMapping("/property")
	public String doPost(@Valid PropertyVO newProp, Errors errors, RedirectAttributes refirectAttributes) throws IOException {
		if (!errors.hasErrors()) { //에러가 있는지 체크
			service.createProperty(newProp);
			String message = "성공";
			refirectAttributes.addFlashAttribute("message",message);
			String viewName = "redirect:/property?name=newProp" + newProp.getPropertyName(); // 이미 위에 property 마샬링하는 애가 있으니까 안만들고																				// 저기로 보내는것임(name가지고)
			return viewName;
		} else {
			return "property/form";
		}
	}
}