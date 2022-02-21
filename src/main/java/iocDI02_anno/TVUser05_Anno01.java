package iocDI02_anno;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

//** Annotation, @, 애노테이션
//=> 컴파일러에게 알려줌

//=> @WebServlet
//=> url 매핑네임을 알려줌 (매핑을 하지 않으면 404 오류발생)
//=> 복수선언 가능(배열형태로)
//   단, 프로젝트내에서 중복되면 절대 안됨!! :  Server Start가 안됨
//=> 한글은 가능 하나 비추
//=> web.xml (설정 파일) 에 설정할수도 있음.
//=> 두 방법은 각자의 장단점이 있다(용도에 맞게 사용)
//   ( @는 수정시 재컴파일 필요, web.xml 은 xml 문을 익혀야함 ..등등 )

//** @ Annotation 어노테이션 활용방법 *********************

//1. xml에 context 네임스페이스 추가  [NameSpaces] 이용
//=> Component-scan 설정  : 
//   <context:component-scan base-package="d0714.iocEx05" />
//	 
//2. 소스코드 
//=> import 확인  : org.springframework.context.support.*;
//=> 생성을 원하는 TV 클래스 위에 @Component("tv") 설정

//=> @Component("tv") 
//   <bean ....> </bean> -> xml
//   new 생성자() -> Java Code

//3.Test : 오류 메시지 확인 하기
//=> @ Test1 생성 -> <bean ... />
//=> @ Test2 id명 미 지정(둘다 beanname 없이) 
//=> ...NoSuchBeanDefinitionException: 
//	  No bean named 'tv' is defined ....
//=> @ Test3 id명 중복되는 경우 (둘다 beanname  ("tv") 지정 )  
//=> ...annotation.ConflictingBeanDefinitionException: ....
//	  ...non-compatible bean definition of same name and class ...

interface TV {
	public void  powerOn();
	public void  powerOff();
	public void  volumeUp();
	public void  volumeDown();
} //interface
//@Component("tv")
class SsTVi implements TV {
	public SsTVi() { System.out.println("~~ SsTVi 생성자 ~~"); } 
	public void  powerOn() { System.out.println("~~ SsTVi powerOn ~~"); }
	public void  powerOff() { System.out.println("~~ SsTVi powerOff ~~"); }
	public void  volumeUp() { System.out.println("~~ SsTVi volumeUp ~~"); }
	public void  volumeDown() { System.out.println("~~ SsTVi volumeDown ~~"); }
} //SsTVi
//@Component("tv")
class LgTVi implements TV {
	public LgTVi() { System.out.println("~~ LgTVi 생성자 ~~"); } 
	public void  powerOn() { System.out.println("~~ LgTVi powerOn ~~"); }
	public void  powerOff() { System.out.println("~~ LgTVi powerOff ~~"); }
	public void  volumeUp() { System.out.println("~~ LgTVi volumeUp ~~"); }
	public void  volumeDown() { System.out.println("~~ LgTVi volumeDown ~~"); }
} //SsTVi

public class TVUser05_Anno01 {

	public static void main(String[] args) {
		// 1. 스프링 컨테이너 구동(생성)
		AbstractApplicationContext sc = new  
			GenericXmlApplicationContext("iocDI02_anno/app05.xml");
	
		// 2. 필요한 객체를 전달받고 서비스 실행 
		TV tv = (TV)sc.getBean("tv"); 
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		
		sc.close();
	} //main
} //class
