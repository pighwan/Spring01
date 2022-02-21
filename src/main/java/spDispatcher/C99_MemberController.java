package spDispatcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import service.MemberService;
import vo.MemberVO;

// ** 스프링MVC : 스프링 DispatcherServlet 사용 
// => IOC/DI 적용한 Ano 기반의 컨트롤러

// ** @Component (bean 생성 @) 의 세분화 
// => 스프링 프레임워크에서는 클래스들을 기능별로 분류하기 위해 @ 을 추가함.
// =>  @Controller:사용자요청을 제어하는 Controller 클래스
//             DispatcherServlet이 해당객체를 Controller객체로 인식하게해줌.    
// =>  @Service : 비즈니스로직을 담당하는 Service 클래스
// =>  @Repository:DB 연동을 담당하는 DAO 클래스
//          DB 연동과정에서 발생하는 예외를 변환 해주는 기능 추가 

// *** @Controller 사용함
// => implements Controller 를 대신함.
// => 아래와 관련된 import 삭제 해야 함.
//    public class LoginController implements Controller {
// => 메서드명, 매개변수, 리턴값 에 자유로워짐 
//   -> 메서드명은 handleRequest 사용안해도 됨
//   -> 매개변수 다양한 정의 가능 (메서드내에서 생성할 필요 없어짐)
//   -> 리턴값은 ModelAndView 또는 String 가능함

// => 요청별 Controller를 한 클래스내에 여러 메서드로 구현할 수 있게 됨  
// => 요청과 메서드 연결 은 @RequestMapping 으로 
//    Controller 클래스 코드내에서 직접 매핑
// => 그러므로 xml 에 설정한 SimpleUrlHandlerMapping 부분은 필요 없음
// => 그러므로 대부분 Table 별로 Controller 클래스를 작성함. 
// => Controller의 Mapping메서드 장점
//    Mapping 메서드의 매개변수로 지정된 객체에 request_ParameterName 과 일치하는 컬럼(setter)존재하면 자동으로 set 해줌
//------------------------------------------------------

@Controller
public class C99_MemberController {
	@Autowired // -> MemberService는 반드시 생성되어 있어야 함
	MemberService service;
	// MemberService service = new memberService();
	
	@RequestMapping(value = "/mlist")
	public ModelAndView mlist(HttpServletRequest request, HttpServletResponse response, ModelAndView mv)
			throws ServletException, IOException {
		// MemberList
		List<MemberVO> list = new ArrayList<MemberVO>();
    	list = service.selectList();
    	if ( list!=null ) {
    		mv.addObject("banana", list);
    	}else {
    		mv.addObject("message", "~~ 출력 자료가 없습니다 ~~");
    	}
    	mv.setViewName("member/memberList");
		return mv;
	} // mlist
	
	@RequestMapping(value = "/mdetail")
	public ModelAndView mdetail(HttpServletRequest request, HttpServletResponse response, ModelAndView mv, MemberVO vo)
			throws ServletException, IOException {
		// MemberDetail
		//vo.setId(request.getParameter("id"));
		// => Controller의 Mapping 메서드의 매개변수로 지정된 객체에 request_ParameterName과 일치하는 컬럼(setter)이 존재하면 자동으로 set해줌
		vo=service.selectOne(vo);
		if (vo != null) {
			mv.addObject("apple", vo);
		}else {
			mv.addObject("message","~~ "+request.getParameter("id")+"님의 자료는 존재하지 않습니다 ~~"); 
		}
		mv.setViewName("member/memberDetail");
		return mv;
	} // mdetail
	
	@RequestMapping(value = "loginf")
	public ModelAndView loginf(HttpServletRequest request, HttpServletResponse response, ModelAndView mv) throws Exception {
		mv.setViewName("member/loginForm");
		return mv;
	} // loginf
	
	@RequestMapping(value = "login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, ModelAndView mv, MemberVO vo)
			throws ServletException, IOException {
		// 1) request 처리
		
		String id = vo.getId();
		String password = vo.getPassword();
		String uri = "member/loginForm";
		
		// 2) service 처리
		vo.setId(id);
		vo = service.selectOne(vo);
		if ( vo != null ) { 
			// ID 는 일치 -> Password 확인
			if ( vo.getPassword().equals(password) ) {
				// Login 성공 -> login 정보 session에 보관, home
				request.getSession().setAttribute("loginID", id);
				request.getSession().setAttribute("loginName", vo.getName());
				uri="home" ;
			}else {
				// Password 오류
				mv.addObject("message", "~~ Password 오류,  다시 하세요 ~~");
			}
		}else {	// ID 오류
			mv.addObject("message", "~~ ID 오류,  다시 하세요 ~~");
		} //else
		
		mv.setViewName(uri);
		return mv;
	} // login
	
	@RequestMapping(value = "logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, ModelAndView mv)
			throws ServletException, IOException {
		
		// ** session 인스턴스 정의 후 삭제하기
    	// => 매개변수: 없거나, true, false
    	// => false : session 이 없을때 null 을 return;
		HttpSession session = request.getSession(false);
    	if (session!=null) session.invalidate();
    	String uri="home";
    	mv.addObject("message", "~~ 로그아웃 되었습니다 ~~"); 
		
    	mv.setViewName(uri);
    	return mv;
	} // logout 
}
