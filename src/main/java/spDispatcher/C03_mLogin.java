package spDispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import service.MemberService;
import service.MemberServiceImpl;
import vo.MemberVO;

public class C03_mLogin implements Controller {
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1) request 처리
		
		ModelAndView mv = new ModelAndView();
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		MemberService service = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
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
	} //doUser 

} //class
