package kr.or.ddit.di.auto;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.sample.view.SampleView;

public class AutoDITestView {
	public static void main(String[] args) {
		/*
		 * 1. 컨테이너 생성
		 * 2. samplview 주입
		 * 3. 구성원 정보 가져오기
		 */
		ConfigurableApplicationContext context = 
				new GenericXmlApplicationContext("/kr/or/ddit/auto/conf/AutoDI-Context.xml");
		
		context.registerShutdownHook();
		
		SampleView view = context.getBean(SampleView.class);
		view.renderMessage(4);
	}
}
