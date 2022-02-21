package aop04;

import org.aspectj.lang.ProceedingJoinPoint;

//** 횡적(공통)관심사항 ( cross concerns => Aspect ) 구현
//=> 횡적(공통)관심사항 과 핵심관심사항 의 연결 방법 xml, @ 방식

//** Boy, Girl => 핵심 관심사항 (core concerns) 만 구현하면 됨.

//** xml 방식의 공통적 관심 사항 구현 3.
//=> 핵심적 관심사항에 인자 와 return값이 있음 
//=> Around 메서드 1개로 구현
//   Before, After_returning, After_throwing, After 을
//   1개의 메서드에서 try~catch~finally 로 처리 

//1) 인자
//=> ProceedingJoinPoint의 getArgs() 메서드
//   핵심관심사항의 인자(매개변수) 의 목록을 배열의 형태로 제공함.
//   around 메서드 내에서 사용가능함.
//   ProceedingJoinPoint 의 부모인 JoinPoint 에 구현되어있음.
//=> 계층도 : JoinPoint (I) -> ProceedingJoinPoint (I)

//2) return 값 처리
//=> around 메서드 내에서 사용 가능하고,
//=> ProceedingJoinPoint의 proceed() 메서드 
//   -> 핵심적 관심사항의 return 값 전달 가능
//=> 반드시 return 해주어야 함.

//** 핵심적 관심사항에 return 값이 있는 경우에는 
//=> Around 에서 반드시 return 해주어야함.  
//=> 안해주면 Main Exception 발생 
//   : org.springframework.aop.AopInvocationException: .....
//=> 이유는 스프링 AOP 동작원리에 있음
//   스프링 AOP는 Proxy(대행자)를 통해서 수행함
//   즉 proceed()에서 정상적으로 메서드를 실행한 후 리턴 값을 주는데
//   가로채서 어떤 action 을 한 후에 기존 리턴 값을 되돌려 주지 않으면
//   가로챈 프록시가 결과 값을 변경하거나 지워버린것과 다름이 없게됨.
//   ( ~AOP.ppt 프록시(Proxy) 참고 )

public class MyAspect {
	
	public Object myAround(ProceedingJoinPoint joinpoint) { 
		// Before: 핵심적 관심사항 수행 이전
		System.out.println("~~ 프로젝트 과제를 해야 됩니다 ~~ => Before");
		
		// 1) return 값 처리
		Object result = null;
		// 2) 핵심관심사항의 인자(매개변수) 사용
		Object[] args = joinpoint.getArgs();
		for (Object o:args) {
			System.out.println("** Before 핵심적 관심사항(joinpoint) 의 매개변수 =>"+o);
		}
		try {
			// PointCut: 핵심적 관심사항 수행
			// => Around 메서드의 매개변수를 통해 전달받아 수행
			// => joinpoint 의 return 값을 전달받아 return
			result = joinpoint.proceed();
			// => Throwable 로 예외처리를 해야함
			
			// After_returning: 핵심적 관심사항 의 정상종료
			System.out.println("~~ 실행이 잘된다 ㅎㅎㅎ ~~ => 핵심적 관심사항 정상종료");
		} catch (Throwable e) {
			// After_throwing: 핵심적 관심사항  의 비정상종료 
			System.out.println("~~ 밤새워 복구한다 zzz ~~ => 예외발생으로 핵심적관심사항 비정상종료 ");
			
			// => 발생된 예외를 Throwable로 처리(처리&종료) 했으므로 main까지 전달되지않음 (확인후 Test)
			throw new RuntimeException(e); // unChecked
			
		} finally {
			// After: 최종(아무튼) 종료 -> 정상 종료이건, 비정상 종료이건 무조건시행
			System.out.println("~~ finally : 무조건 제출한다 ~~ => 아무튼 종료");
		}
		return result;
	} //myAround

} // class
