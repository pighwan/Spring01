package aop02;

//** 횡적(공통)관심사항 ( cross concerns => Aspect ) 구현
//=> 횡적(공통)관심사항 과 핵심관심사항 의 연결 방법 xml, @ 방식

//** Boy, Girl => 핵심 관심사항 (core concerns) 만 구현하면 됨.

//** xml 방식의 공통적 관심 사항 구현 1.
//=> 개별 advice 메서드 구현
//   Before, After_returning, After_throwing, After 

//** 용어정리
// Target: 핵심사항(Core concerns) 가 구현된 객체 : Boy, Girl
// JoinPoint: 공통관심사항이 적용될수있는 지점, 클라이언트가 호출하는 모든 비즈니스 메서드   
//            (ex:메소드 호출시, 객체생성시 등)
// Pointcut: JoinPoint 중 실제 공통사항이 적용될 대상 : doStudying()  
// Advice: 공통관심사항(Cross-Cutting) 구현 코드 + 적용시점
//		 : 시점별 메서드들 ( myBefore() .... )
//		 : 적용시점 (핵심로직 실행 전, 후, 정상 종료 후, 비정상 종료 후 등 )
// Aspect: Advice + Pointcut

//** pom.xml 설정 
// 1) AspectJ  
// 2) AspectJ Weaver : AOP 실습시에 반드시 추가 해줄것 
//    => weaver가 AOP에서 advice를 핵심기능에 적용하는 설정 파일  

public class MyAspect {
	
	// Before : 핵심적 관심사항 수행 이전
	public void myBefore() {
		System.out.println("~~ 프로젝트 과제를 해야 됩니다 ~~ => Before");
	}
	// After_returning : 핵심적 관심사항 의 정상종료
	public void myAfter_returning() {
		System.out.println("~~ 실행이 잘된다 ㅎㅎㅎ ~~ => 핵심적 관심사항 정상종료");
	}
	// After_throwing : 핵심적 관심사항  의 비정상종료
	public void myAfter_throwing() {
		System.out.println("~~ 밤새워 복구한다 zzz ~~ => 예외발생으로 핵심적관심사항 비정상종료 ");
	}
	// After : 핵심적 관심사항  의 종료
	//  => 정상 종료이건, 비정상 종료이건 무조건시행
	public void myAfter() {
		System.out.println("~~ finally : 무조건 제출한다 ~~ => 아무튼 종료");
	}

} // class
