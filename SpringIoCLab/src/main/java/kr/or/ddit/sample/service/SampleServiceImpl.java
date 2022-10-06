package kr.or.ddit.sample.service;

import java.util.Arrays;

import kr.or.ddit.sample.dao.SampleDAO;
import kr.or.ddit.sample.dao.SampleDAOFactory;
import kr.or.ddit.sample.dao.SampleDAOImpl_Oracle;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleServiceImpl implements SampleService{
	//1. 결합력 최상
	//private SampleDAO dao = new SampleDAOImpl_Oracle();
	
	//2. Factory object pattern
	//private SampleDAO dao = new SampleDAOFactory().getSampleDAO();
	
	//3.Strategy Pattern : 전략 주입자 필요.(주입할 수 있는 방법 필요. ->setter와 생성자)
	//4. DI Container활용.(주입자를 어플리케이션 밖으로 꺼낸다.)
	private SampleDAO dao;
	
	public SampleServiceImpl() {
		super();
		log.info("{}객체 생성, 기본 생성자", this);
	}
	
	public SampleServiceImpl(SampleDAO dao) {
		super();
		log.info("{}객체 생성, dao 파라미터를 받는 생성자", this);
		this.dao = dao;
	}
	public void setDao(SampleDAO dao) {
		log.info("dao 를 setter에서 주입받음.");
		this.dao = dao;
	}
	
	public void start() { //원래는 init메서드지만 이 클래스는 POJO객체이므로 자유롭게 만들 수 있음
		log.info("{}객체 초기화(주입) 완료. {}", this, dao);
	}
	
	public void stop() { //원래는 destroy메서드지만 이 클래스는 POJO객체이므로 자유롭게 만들 수 있음
		log.info("{}객체 소멸", this);
	}
	
	@Override
	public String retiveTeam(Integer teamNumber) {
		String[] rawdata = dao.selectTeam(teamNumber);
		String information = Arrays.toString(rawdata);
		return information;
	}

}
