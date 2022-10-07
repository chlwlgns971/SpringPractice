package kr.or.ddit.sample.view;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import kr.or.ddit.sample.dao.SampleDAO;
import kr.or.ddit.sample.dao.SampleDAOImpl_Oracle;
import kr.or.ddit.sample.service.SampleService;
import kr.or.ddit.sample.service.SampleServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SampleView {
	private SampleService service;
	
	public SampleView() {
		log.info("{} 객체 생성",this);
	}
	//@Autowired
	@Required
	@Inject
	public void setService(SampleService service) {
		this.service = service;
	}
	public void renderMessage(Integer teamNumber) {
		String content = String.format("조회한 모델 데이터 : %s\n", service.retiveTeam(teamNumber));
		System.out.println(content);
	}
	
	public void start() { //원래는 init메서드지만 이 클래스는 POJO객체이므로 자유롭게 만들 수 있음
		log.info("{}객체  초기화 완료.", this);
	}
	
	public void stop() { //원래는 destroy메서드지만 이 클래스는 POJO객체이므로 자유롭게 만들 수 있음
		log.info("{}객체 소멸", this);
	}
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("kr/or/ddit/sample/conf/Sample-Context.xml");
		//Ioc -> Dependency Injection
		
		SampleView view = context.getBean(SampleView.class);
		view.renderMessage(4);
	}
}

