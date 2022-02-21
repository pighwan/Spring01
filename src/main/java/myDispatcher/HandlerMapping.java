package myDispatcher;

import java.util.HashMap;
import java.util.Map;

//** 요청에 대한 ServiceController 를 생성 후 제공
//=> 싱글톤 패턴
//=> 요청명 대 ServiceController 는 Map 으로 처리

public class HandlerMapping {
	// ** Map 정의
	private Map<String, MyController> mappings;
	
	// ** 생성자에서 Map 초기화
	private HandlerMapping() {
		mappings = new HashMap<String, MyController>();
		mappings.put("/mlist", new C01_mList());
		mappings.put("/mdetail", new C02_mDetail());
		mappings.put("/login", new C03_mLogin());
		mappings.put("/logout", new C04_mLogout());
	} //생성자 
		
	// ** 싱글톤 패턴
	// => 생성자를 private 으로, 메서드를 이용하여 생성해서 전달 
	private static HandlerMapping instance = new HandlerMapping();
	public static HandlerMapping getInstance() { return instance; }  
	
	// ** getController
	public MyController getController(String mappingName) {
		return mappings.get(mappingName);
	}
	
} //class
