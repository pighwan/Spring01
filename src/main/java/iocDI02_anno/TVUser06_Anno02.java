package iocDI02_anno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

//** TV 는 Speaker 를 사용 (Speaker 를 의존)
//** 의존성 해결 
//=> 생성자 주입 & setter 주입 : @ 방법

//** @Autowired
//=> 맴버변수에 직접 생성된 주소값을 전달
//   그러므로 Speaker 는 반드시 이미 생성되어 있어야 함.
//=> 자동주입: 있으면 주입 , 없으면 Exception 발생
//=> 적용순위
//1) 주로 멤버변수 위에 선언하며,
//	  스프링 컨테이너는 해당변수의 타입을 체크하고,
//	  그 타입의 객체가 메모리에 존재하는지 확인하여
//	  해당 변수에 대입 해준다.
//2) 동일타입의 객체가 둘이상이면  @Qualifier 에 명시된 객체 주입
//3) 동일타입의 객체가 둘이상이고  @Qualifier 없으면 
//   id가 같은 객체 찾아 있으면 주입, 없으면 오류

//** Test : Speaker 생성 안되어있는 경우 오류메시지 확인
//		=> ...Injection of autowired dependencies failed;.....

//** required 속성
//	=> true : 해당 타입의 등록된 빈이 없을때 주입시 익셉션이 발생
//	=> false: 해당 타입의 등록된 빈이 없을때 익셉션이 발생하지 않음 
//			( 있으면주입 , 없으면 null ) 	
//	
//1) @Autowired(required=true) : default 
//		=> BeanCreationException <- NoSuchBeanDefinitionException: .... as autowire...  
//2) @Autowired(required=false) 
//		=> 실행문에서 접근시 NullPointerException 발생

//@Component
class Speaker {
	public Speaker() { System.out.println("~~ Speaker 생성자 ~~"); } 
	public void  volumeUp() { System.out.println("~~ Speaker volumeUp ~~"); }
	public void  volumeDown() { System.out.println("~~ Speaker volumeDown ~~"); }
}

// ** TV는 Speaker를 사용 ( TV 는 Speaker에 대한 의존관계성립 )
// => 의존성 해결 방법 
// 1) 고전적 방법
// => 직접 new : 스피커교체시 소스 수정 & 재컴파일
//@Component("tvs")
class SsTVs implements TV {
	private Speaker sp = new Speaker();
	
	public SsTVs() { System.out.println("~~ SsTVs 생성자 ~~"); } 
	public void  powerOn() { System.out.println("~~ SsTVs powerOn ~~"); }
	public void  powerOff() { System.out.println("~~ SsTVs powerOff ~~"); }
	public void  volumeUp() { sp.volumeUp(); }
	public void  volumeDown() { sp.volumeDown(); }
} //SsTVs

// 2) IOC/DI : 생성자 주입	
//@Component("tvl")
class LgTVs implements TV {
	
	//@Autowired(required = false)
	// ** 자동주입
	// => 해당객체 (Speaker) 는 반드시 이미 생성되어 있어야함. 아래 구문의 = (대입) 역할
	//    Speaker sp = new Speaker();
	// => 메모리에서 타입이 일치하는 객체를 찾아 있으면 제공
	// ** required = false
	// => 못찾으면 null return => NullPointExceptiopn 발생
	private Speaker sp;
	// => 주입 받으려는 객체위에 정의 (아래를 참고)
	//	@Autowired
	//	private Person ps;
	//	@Autowired
	//	private  Student st;
	
	private String color;
	private int price;
		
	public LgTVs() { System.out.println("~~ LgTVs 생성자 ~~"); } 
	// 맴버변수 초기화를 하는 생성자를 추가
	public LgTVs(Speaker sp, String color, int price) { 
		this.sp=sp;
		this.color=color;
		this.price=price;
		System.out.println("~~ LgTVs 맴버변수 초기화 생성자 => "+color+price); 
	} 
	
	public void  powerOn() { System.out.println("~~ LgTVs powerOn ~~"); }
	public void  powerOff() { System.out.println("~~ LgTVs powerOff ~~"); }
	public void  volumeUp() { sp.volumeUp(); }
	public void  volumeDown() { sp.volumeDown(); }
} //LgTVs

//3) IOC/DI : Setter 주입 
//@Component("tva")
class AiTVs implements TV {
	//@Autowired
	private Speaker sp;
	private String color;
	private int price;
		
	public AiTVs() { System.out.println("~~ AiTVs 생성자 ~~"); } 
	
	// 맴버변수 초기화를 하는 Setter 를 추가
	public void setSp(Speaker sp) { this.sp=sp; }
	public void setColor(String color) { 
		this.color=color; 
		System.out.println("~~ color setter주입 => "+color); 
	}
	public void setPrice(int price) { 
		this.price=price; 
		System.out.println("~~ price setter주입 => "+price); 
	}
	
	public void  powerOn() { System.out.println("~~ AiTVs powerOn ~~"); }
	public void  powerOff() { System.out.println("~~ AiTVs powerOff ~~"); }
	public void  volumeUp() { sp.volumeUp(); }
	public void  volumeDown() { sp.volumeDown(); }
} //AiTVi

public class TVUser06_Anno02 {

	public static void main(String[] args) {
		// 1. 스프링 컨테이너 구동
		AbstractApplicationContext sc = new  
			GenericXmlApplicationContext("iocDI02_anno/app05.xml");
		
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
		
//		System.out.println("**  Test3) IOC/DI : Setter 주입  **");
//		TV tva = (TV)sc.getBean("tva") ;
//		tva.powerOn();
//		tva.volumeUp();
//		tva.volumeDown();
//		tva.powerOff();		

		sc.close();
	} // main
} //class
