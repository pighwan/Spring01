package aop04;

import java.util.Random;

//** Aop 구현
// 1 단계 : 핵심적 관심사항 과  공통적 관심사항 분리
// => 핵심적 관심사항만 구현
// => 공통적 관심사항(Aspect) 분리 : 별도의 클래스로 분리 -> MyAspect.java

public class Boy implements Person {
	
	public Boy() { System.out.println("** Boy default 생성자 **"); }

	@Override
	public String doStudying(int n) throws Exception  {
		
		System.out.println(" ~~ 게시판을 "+n+"개 만듭니다 ~~ => 핵심적 관심사항");
		
		//if (new Random().nextBoolean()) // true : 실패
		if (true) // true : 실패 -> 항상 비정상종료 되도록 
			throw new Exception("~~ 홀랑 다 날렸다 ㅠㅠㅠ !!! ~~ => 예외발생");
		return "네이버 취업성공" ;	
	} //doStudying
	
} //Boy
