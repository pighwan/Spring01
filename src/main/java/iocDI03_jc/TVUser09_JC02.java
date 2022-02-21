package iocDI03_jc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

//** Java bean configuration class를 이용한 DI
//=> Bean 컨테이너 : AnnotationConfigApplicationContext 사용
//=> Test02 : 스피커 1개 사용 TV

class Speaker {
	public Speaker() { System.out.println("~~ Speaker 생성자 ~~"); } 
	public void  volumeUp() { System.out.println("~~ Speaker volumeUp ~~"); }
	public void  volumeDown() { System.out.println("~~ Speaker volumeDown ~~"); }
}

// ** TV는 Speaker를 사용 ( TV 는 Speaker에 대한 의존관계성립 )
// 1) 고전적 방법
class SsTVs implements TV {
	private Speaker sp = new Speaker();
	
	public SsTVs() { System.out.println("~~ SsTVs 생성자 ~~"); } 
	public void  powerOn() { System.out.println("~~ SsTVs powerOn ~~"); }
	public void  powerOff() { System.out.println("~~ SsTVs powerOff ~~"); }
	public void  volumeUp() { sp.volumeUp(); }
	public void  volumeDown() { sp.volumeDown(); }
} //SsTVs

// 2) IOC/DI : 생성자 주입	
class LgTVs implements TV {
	
	private Speaker sp;
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

//3) IOC/DI : xml 병행사용 Test 
class AiTVs implements TV {
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

public class TVUser09_JC02 {

	public static void main(String[] args) {
		// 1. 스프링 컨테이너 구동
		AbstractApplicationContext sc = new  
			AnnotationConfigApplicationContext(JavaConfig02.class);
		
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
