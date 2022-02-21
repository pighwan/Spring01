package aop05;

import java.util.Random;

//** Aop 구현
//1 단계 : 핵심적 관심사항 과  공통적 관심사항 분리
//=> 핵심적 관심사항만 구현
//=> 공통적 관심사항(Aspect) 분리 : 별도의 클래스로 분리 -> MyAspect.java

public class Girl implements Person {

	public Girl() { System.out.println("** Girl default 생성자 **"); }
	
	@Override
	public String doStudying(int n, int p) throws Exception {
		
		System.out.println(" ~~ 회원관리를 "+(n+p)+"개 만듭니다 ~~ => 핵심적 관심사항");
		// if (new Random().nextBoolean()) { // 실패
		if (1==2) // false : 성공 -> 항상 정상종료 되도록
			throw new Exception("~~ 홀랑 다 날렸다 ㅠㅠㅠ !!! ~~ => 예외발생");
		return "구글 취업성공" ;	
				
	} //doStudying
} //Girl
