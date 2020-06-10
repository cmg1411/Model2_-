package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;

public class Idcheck implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		System.out.println("아이디 중복검사 서비스");
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		System.out.println("id : "+id);
		
		MemberDAO dao = new MemberDAO();
		int result = dao.idcheck(id);
		System.out.println("sql result : " + result);
		//1 - 중복아이디, -1 - 사용가능아이디
		
		PrintWriter out = response.getWriter();
		out.println(result); // 비동기 ajax로 요청했기 때문에 브라우저로 callback함수를 이용해서 반환.
		
		return null;
	}

}
