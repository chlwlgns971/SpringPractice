package kr.or.ddit.batch.ver1;

public class PrintNumberJobTest{
	static class PrintNumberJob implements Runnable{
		public void run() {
			for(int i=1; i<=20; i++) {
				System.out.printf("%d - %s\n",i, Thread.currentThread().getName());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	static class PrintNumberJobGenerateJob implements Runnable{
		@Override
		public void run() {
			while(true) {
				new Thread(new PrintNumberJob()).start();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}
	}
	
	public static void main(String[] args) {
		/**
		 * 1부터 20까지 숫자를 매 1초마다 반복적으로 콘솔에 출력할 것.
		 * 상기의 작업을 어플리케이션의 다른 기능에는 전혀 영향이 없이 실행할 것.
		 */
		new Thread(new PrintNumberJobGenerateJob()).start();
		
		System.out.println("어플리케이션의 다른 기능");
	}
	
}
