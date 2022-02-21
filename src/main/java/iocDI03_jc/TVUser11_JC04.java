package iocDI03_jc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

// ** Java bean configuration class를 이용한 DI
//=> Test04 : xml 을 이용해서 JC 사용하기 
//   xml 용 컨테이너 GenericXmlApplicationContext 사용
//=> 스피커 2개 중 선택

public class TVUser11_JC04 {

	public static void main(String[] args) {
		// 1. 스프링 컨테이너 구동
		AbstractApplicationContext sc = new  
				GenericXmlApplicationContext("iocDI03_jc/applicationContext04.xml") ;
		
		// 2. 필요한 객체(TV) 를 전달 & 서비스 실행
		System.out.println("**  Test1) 고전적방법 : 직접 new  **");
		TV tvs = (TV)sc.getBean("tvs") ;
		tvs.powerOn();
		tvs.volumeUp();
		tvs.volumeDown();
		tvs.powerOff();
		
		System.out.println("**  Test2) IOC/DI : 생성자 주입  **");
		TV tvl = (TV)sc.getBean("tvl") ;
		tvl.powerOn();
		tvl.volumeUp();
		tvl.volumeDown();
		tvl.powerOff();
		
		System.out.println("**  Test3) IOC/DI : Setter 주입  **");
		TV tva = (TV)sc.getBean("tva") ;
		tva.powerOn();
		tva.volumeUp();
		tva.volumeDown();
		tva.powerOff();		

		sc.close();
	} // main
} //class
