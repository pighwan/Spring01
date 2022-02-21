package lifeCycle02_All;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;



// ** Bean(객체) 의 LifeCycle 
// => 객체생성 -> 사용 -> 소멸
// => 해당되는 특정시점에 자동실행 : xml, @, interface

// ** Test 4. ALL
// => 실행순서 : ? -> ? -> ?

class LifeCycleTest implements InitializingBean, DisposableBean{
	public LifeCycleTest() {System.out.println("~~ LifeCycleTest_ALL 생성자 ~~");}
	
	// 1. xml 적용 메서드
	public void begin() {System.out.println("~~ LifeCycleTest_ALL begin() ~~");}
	public void end() {System.out.println("~~ LifeCycleTest_ALL end() ~~");}
	
	// 2. @, Annotation 적용
	@PostConstruct
	public void beginAnno() {System.out.println("~~ LifeCycleTest_ALL @PostConstruct ~~");}
	@PreDestroy
	public void endAnno() {System.out.println("~~ LifeCycleTest_ALL @PreDestroy ~~");}
	
	// 3. interface 적용
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("~~ LifeCycleTest_ALL afterPropertiesSet ~~");}
	@Override
	public void destroy() throws Exception {
		System.out.println("~~ LifeCycleTest_ALL destroy() ~~");}
	
	public void list() {System.out.println("~~ LifeCycleTest_ALL list() ~~");}
	public void login() {System.out.println("~~ LifeCycleTest_ALL login() ~~");}
}	// LifeCycleTest

public class LC04_All {

	public static void main(String[] args) {
		AbstractApplicationContext sc = new
				GenericXmlApplicationContext("lifeCycle02_All/lc_All.xml");
		LifeCycleTest lc = (LifeCycleTest)sc.getBean("lc");
		lc.login();
		lc.list();
		sc.close();

	}	// main

}	// class
