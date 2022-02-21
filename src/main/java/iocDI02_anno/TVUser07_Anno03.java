package iocDI02_anno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

//** 생성자 주입 & setter 주입 => 명시적 자동주입
//=>  @Autowired , @Qualifier("spA") 

//** TV 는 Speaker 를 사용 (Speaker 를 의존)
//=> Speaker 가 여러개

//** Test1) : spA, spB 생성후 @Autowired 만 한 경우 
// ...UnsatisfiedDependencyException: Error creating bean with name 'tva': Unsatisfied 
// ....matching bean but found 2: spA,spB
//** Test2) : spA 만 생성후 @Autowired 만 한 경우 
// => Type 일치 하는경우가 1개이기 때문에 정상실행

//=> xml, @ 병행 가능
//   Speaker 의 생성은 xml 에 <bean ..> 등록후 Test

interface Speakeri {
	void  volumeUp();
	void  volumeDown();
} //Speakeri

//@Component("spA") -> xml 에서 처리
class SpeakerA implements Speakeri {
	public SpeakerA() { System.out.println("~~ SpeakerAAA 생성자 ~~"); } 
	public void  volumeUp() { System.out.println("~~ SpeakerAAA volumeUp ~~"); }
	public void  volumeDown() { System.out.println("~~ SpeakerAAA volumeDown ~~"); }
} //SpeakerA
//@Component("spB") -> xml 에서 처리
class SpeakerB implements Speakeri {
	public SpeakerB() { System.out.println("~~ SpeakerBBB 생성자 ~~"); } 
	public void  volumeUp() { System.out.println("~~ SpeakerBBB volumeUp ~~"); }
	public void  volumeDown() { System.out.println("~~ SpeakerBBB volumeDown ~~"); }
} //SpeakerA

//** Speaker에 대한 의존성(Dependency) 해결
//1) 고전적방법 
//=> 직접 new : 스피커 A, B 교체시 소스수정 & 재컴파일
@Component("tvs")
class SsTVsi implements TV {
	private Speakeri sp = new SpeakerA();
	
	public SsTVsi() { System.out.println("~~ SsTVsi 생성자 ~~"); } 
	public void  powerOn() { System.out.println("~~ SsTVsi powerOn ~~"); }
	public void  powerOff() { System.out.println("~~ SsTVsi powerOff ~~"); }
	public void  volumeUp() { sp.volumeUp(); }
	public void  volumeDown() { sp.volumeDown(); }
} //SsTVs

// 2) IOC/DI => SpeakderA 또는 B 를 생성자 주입
@Component("tvl")
class LgTVsi implements TV {
	
	@Autowired
	@Qualifier("spB")
	private Speakeri sp;
	private String color;
	private int price;
		
	public LgTVsi() { System.out.println("~~ LgTVsi 생성자 ~~"); } 
	// 맴버변수 초기화를 하는 생성자를 추가
	public LgTVsi(Speakeri sp, String color, int price) { 
		this.sp=sp;
		this.color=color;
		this.price=price;
		System.out.println("~~ LgTVsi 맴버변수 초기화 생성자 => "+color+price); 
	} 
	
	public void  powerOn() { System.out.println("~~ LgTVsi powerOn ~~"); }
	public void  powerOff() { System.out.println("~~ LgTVsi powerOff ~~"); }
	public void  volumeUp() { sp.volumeUp(); }
	public void  volumeDown() { sp.volumeDown(); }
} //LgTVs

//3) IOC/DI : Setter 주입 
@Component("tva")
class AiTVsi implements TV {
	@Autowired
	@Qualifier("spA")
	private Speakeri sp;
	private String color;
	private int price;
		
	public AiTVsi() { System.out.println("~~ AiTVsi 생성자 ~~"); } 
	
	// 맴버변수 초기화를 하는 Setter 를 추가
	public void setSp(Speakeri sp) { this.sp=sp; }
	public void setColor(String color) { 
		this.color=color; 
		System.out.println("~~ color setter주입 => "+color); 
	}
	public void setPrice(int price) { 
		this.price=price; 
		System.out.println("~~ price setter주입 => "+price); 
	}
	
	public void  powerOn() { System.out.println("~~ AiTVsi powerOn ~~"); }
	public void  powerOff() { System.out.println("~~ AiTVsi powerOff ~~"); }
	public void  volumeUp() { sp.volumeUp(); }
	public void  volumeDown() { sp.volumeDown(); }
} //AiTVi

public class TVUser07_Anno03 {

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
		
		System.out.println("**  Test3) IOC/DI : Setter 주입  **");
		TV tva = (TV)sc.getBean("tva") ;
		tva.powerOn();
		tva.volumeUp();
		tva.volumeDown();
		tva.powerOff();		

		sc.close();
	} // main
} //class
