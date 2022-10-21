package kr.or.ddit.batch.ver5;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PrintNumberJob {
	private int number;
	
	//@Scheduled(initialDelay=0, fixedRate=1000)
	//@Scheduled(cron="0 0 3 ? * MON")//초,분,시,일,월,요일 -> 매주 월요일 새벽3시에 반복
	public void printNumber() {
		System.out.printf("%d - %s\n",++number, Thread.currentThread().getName());

	}
}
