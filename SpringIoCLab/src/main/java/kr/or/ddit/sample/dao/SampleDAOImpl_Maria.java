package kr.or.ddit.sample.dao;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleDAOImpl_Maria implements SampleDAO {
	private static final Map<Integer, String[]> TEAMTABLE = new LinkedHashMap<Integer, String[]>();
	
	
	public SampleDAOImpl_Maria() {
		super();
		TEAMTABLE.put(1, new String[] {"이유화_maria", "오용택_maria", "정경환_maria", "윤정식_maria", "이원걸_maria", "이찬솔_maria"});
		TEAMTABLE.put(2, new String[] {"김호겸_maria", "임찬우_maria", "장혜연_maria", "임지수_maria", "이주원_maria", "장윤식_maria"});
		TEAMTABLE.put(3, new String[] {"이유영_maria", "방형준_maria", "강은비_maria", "김건호_maria", "최현우_maria", "구지현_maria"});
		TEAMTABLE.put(4, new String[] {"최지훈_maria", "홍무곤_maria", "정요한_maria", "강명범_maria", "김유리_maria", "조수빈_maria"});
	}
	
	public void start() { //원래는 init메서드지만 이 클래스는 POJO객체이므로 자유롭게 만들 수 있음
		log.info("{}객체 생성 후 초기화 완료.", this);
	}
	
	public void stop() { //원래는 destroy메서드지만 이 클래스는 POJO객체이므로 자유롭게 만들 수 있음
		log.info("{}객체 소멸", this);
	}
	
	@Override
	public String[] selectTeam(Integer teamNumber) {
		return TEAMTABLE.get(teamNumber);
	}

}
