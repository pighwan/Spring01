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

public class C02_mDetail implements Controller {
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// MemberDetail
		ModelAndView mv = new ModelAndView();
		MemberService service= new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setId(request.getParameter("id"));
		vo=service.selectOne(vo);
		if (vo != null) {
			mv.addObject("apple", vo);
		}else {
			mv.addObject("message","~~ "+request.getParameter("id")+"님의 자료는 존재하지 않습니다 ~~"); 
		}
		mv.setViewName("member/memberDetail");
		return mv;
	}

} //F06_mDetail
