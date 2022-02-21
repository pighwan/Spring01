package iocDI03_jc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

//** Java bean configuration class를 이용한 DI
//=> Test03 : 스피커 2개중 선택 
//=> 생성자 를 이용한 주입..

//*** JC 와 @ 병행사용
//*** JC , @, xml 병행사용
//=> JC 내에서 xml 로 생성된 객체를 직접 사용하는것은 허용 되지 않음. 
//   단, User 클래스에서는 사용가능

//** 실습
// => SsTVsi , Speaker 는 xml 로 생성
// => LgTVsi, AiTVsi 는 JC 의 @Bean 으로 생성
// => LgTVsi (Speaker 생성자주입) 
//    AiTVsi (Speaker @Autowired)   

@ImportResource("iocDI03_jc/applicationContext03.xml")
@Configuration
public class JavaConfig03 {
	@Bean
	public TV tvl() {
		return new LgTVsi(spB(), "Blue", 22335500) ;
	}
	//@Bean => User 로 전달안됨 확인.
	public Speakeri spA() { return new SpeakerA(); }
	public Speakeri spB() { return new SpeakerB(); }
	// => @Autowired 를 위해 @Qualifier("spA") 에 전달해야 하므로  
	//    @Bean 이 필요함. (적용 전,후 오류메시지를 확인해본다)
	// => JC 코드내에서만 사용하는 spB() 는 @Bean 이 필요없음 
	// => spA , xml 에서 생성후 User 로 전달됨 확인.
	@Bean
	public TV tva() { return new AiTVsi(); }
	// => @Autowired Test
	
	
	
	
} //class
