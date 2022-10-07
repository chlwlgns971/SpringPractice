package kr.or.ddit.di;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VariousDITestView {
	public static void main(String[] args) {
		/*
		 * 1. 컨테이너 객체 생성(단 위치는 동적으로 변경)
		 * 2. 자동종료되게 설정
		 * 3. log찍기
		 */
		String cpath = "classpath:/kr/or/ddit/di/conf/VariousDI-Context.xml";
		ConfigurableApplicationContext context = new GenericXmlApplicationContext(cpath);
		
		context.registerShutdownHook();
		
		VariousDIVO divo = context.getBean("vo1", VariousDIVO.class);
		log.info("주입된 객체 : {}", divo);
		log.info("fsFile : {}", divo.getFsfile());
		log.info("cpFile : {}", divo.getCpfile());
		
	}
}
