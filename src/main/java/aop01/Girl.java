package aop01;

import java.util.Random;

public class Girl implements Person {

	@Override
	public void doStudying() {
		System.out.println("~~ 프로젝트 과제를 해야 됩니다 ~~ => Before");
		try {
			System.out.println(" ~~ 회원관리를 만듭니다 ~~ => 핵심적 관심사항");
			if (new Random().nextBoolean()) {
					// 실패
				throw new Exception("~~ 홀랑 다 날렸다 ㅠㅠㅠ !!! ~~ => 예외발생");
			}else // 성공
				 System.out.println("~~ 회원가입이 잘된다 ㅎㅎㅎ ~~ => 핵심적 관심사항 정상종료");
		} catch (Exception e) {
			System.out.println("** Girl Exception => "+e.toString());
			System.out.println("~~ 밤새워 복구한다 zzz ~~ => 예외발생으로 핵심적관심사항 비정상종료 ");
		} finally {
			System.out.println("~~ finally : 무조건 제출한다 ~~ => 아무튼 종료");
		}		
	} //doStudying
} //Girl
