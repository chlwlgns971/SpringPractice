package kr.or.ddit.sample.view;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DIContainerTestView {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new GenericXmlApplicationContext("classpath:/kr/or/ddit/sample/conf/DI-Context.xml");
		
		//객체가 소멸될 시점을 예측하고 종료시킴
		context.registerShutdownHook();
		
//		SampleView view1 = (SampleView) context.getBean("sampleView");
//		SampleView view2 = (SampleView) context.getBean("sampleView2");
//		log.info("주입된 객체 : {}",view1);
//		log.info("주입된 객체 : {}",view2);
//		
//		log.info("동일객체 여부 : {}", view1==view2);
	}
}
