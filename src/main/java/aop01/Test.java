package aop01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		// ** 순수 Java
		//Person programmerB = new Boy();
		//Person programmerG = new Girl();
		
		// ** IOC/DI 적용: 스프링 컨테이너를 통해 주입받기
		AbstractApplicationContext sc = 
				new GenericXmlApplicationContext("aop01.xml");
		Person programmerB = (Person)sc.getBean("boy");
		Person programmerG = (Person)sc.getBean("girl");
		
		programmerB.doStudying();
		programmerG.doStudying();
		sc.close();
	}//main

} //class
