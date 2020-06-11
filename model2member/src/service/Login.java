package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;

public class Login implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("Login");
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		MemberDAO dao = MemberDAO.getInstance();
		int result = dao.memberAuth(id, passwd);
		System.out.println("result : " + result);
		
		if(result==1) {
			System.out.println("회원인증 성공");
			session.setAttribute("id", id);
		}else {
			out.println("<script>");
			out.println("alert('로그인실패');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.flush();
			
			return null;
		}
		

		ActionForward actionforward = new ActionForward();
		actionforward.setRedirect(false); 
		actionforward.setPath("/member/main.jsp");

		return actionforward;
	}

}
