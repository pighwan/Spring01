package myDispatcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;
import service.MemberServiceImpl;
import vo.MemberVO;

public class C01_mList implements MyController {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// MemberList
		MemberService service= new MemberServiceImpl();
		List<MemberVO> list = new ArrayList<MemberVO>();
    	list = service.selectList();
    	if ( list!=null ) {
    		request.setAttribute("banana", list);
    	}else {
    		request.setAttribute("message", "~~ 출력 자료가 없습니다 ~~");
    	}
		return "member/memberList";
	}
	
} //C01_mList 
