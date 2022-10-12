package kr.or.ddit.etc.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.board.vo.CalculateVO;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@RequestMapping("/calculate")
public class CalculateController {
	@RequestMapping("form")
	public String calForm() {
		return "etc/calculateForm";
	}
	/**
	 * 만약404에러가 난 경우, url주소와 웹에 띄어진 주소가 일치하는지 확인한다.
	 * 만약 일치한다면 컨트롤러를 못찾아서 난 에러이고, 일치하지 않는다면 뷰를 찾이 못해 일어난 에러이다.
	 */
//	case1. JSON->JSON
	@PostMapping(consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public CalculateVO jsonToJson(@RequestBody CalculateVO vo) {
		return vo;
	}
//	case2. JSON -> HTML
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String jsonToHtml(@RequestBody CalculateVO vo, Model model) {
		model.addAttribute("calculate", vo);
		return "/etc/result";
	}
//	case3. parameter -> JSON
	/**
	 * 위에서 JSON데이터로 받을 땐 @RequestBody 태그를 사용해서 파라미터를 받았다.
	 * 원래 데이터를 받을 때 기본으로 parameter값으로 받는데, 이 값이 JSON일 경우 @RequestBody태그를 사용하여
	 * 메세지 형식이라는 것을 명시해줘야 한다. 따라서 일반적인 parameter에 request 태그를 붙일경우 파라미터가 아닌 메세지라는 것을 의미하기 때문에 서버에서 처리를 할 수 없다는
	 * 415 에러가 발생하게 된다.
	 *  
	 */
	@PostMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public CalculateVO parameterToJson(CalculateVO vo) {
		return vo;
	}
//	case4. parameter -> HTML
	@PostMapping
	public String parameterToHtml(@ModelAttribute("calculate") CalculateVO vo) {
		return "/etc/result";
	}
}
