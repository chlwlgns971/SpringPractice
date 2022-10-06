package kr.or.ddit.sample.dao;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleDAOImpl_Oracle implements SampleDAO {
	private static final Map<Integer, String[]> TEAMTABLE = new LinkedHashMap<Integer, String[]>();
	
	
	public SampleDAOImpl_Oracle() {
		super();
		log.info("{} 객체 생성", this);
		TEAMTABLE.put(1, new String[] {"이유화_oracle", "오용택_oracle", "정경환_oracle", "윤정식_oracle", "이원걸_oracle", "이찬솔_oracle"});
		TEAMTABLE.put(2, new String[] {"김호겸_oracle", "임찬우_oracle", "장혜연_oracle", "임지수_oracle", "이주원_oracle", "장윤식_oracle"});
		TEAMTABLE.put(3, new String[] {"이유영_oracle", "방형준_oracle", "강은비_oracle", "김건호_oracle", "최현우_oracle", "구지현_oracle"});
		TEAMTABLE.put(4, new String[] {"최지훈_oracle", "홍무곤_oracle", "정요한_oracle", "강명범_oracle", "김유리_oracle", "조수빈_oracle"});
	}
	
	public void start() { //원래는 init메서드지만 이 클래스는 POJO객체이므로 자유롭게 만들 수 있음
		log.info("{}객체 초기화 완료.", this);
	}
	
	public void stop() { //원래는 destroy메서드지만 이 클래스는 POJO객체이므로 자유롭게 만들 수 있음
		log.info("{}객체 소멸", this);
	}

	@Override
	public String[] selectTeam(Integer teamNumber) {
		return TEAMTABLE.get(teamNumber);
	}

}
