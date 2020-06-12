package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import model.MemberDTO;

public class Update implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(this.getClass().getName());
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		
		MemberDTO member = new MemberDTO();
		member.setId(request.getParameter("id"));
		member.setPasswd(request.getParameter("passwd"));
		member.setName(request.getParameter("name"));
		member.setJumin1(request.getParameter("jumin1"));
		member.setJumin2(request.getParameter("jumin2"));
		member.setMailid(request.getParameter("mailid"));
		member.setDomain(request.getParameter("domain"));
		member.setTel1(request.getParameter("tel1"));
		member.setTel2(request.getParameter("tel2"));
		member.setTel3(request.getParameter("tel3"));
		member.setPhone1(request.getParameter("phone1"));
		member.setPhone2(request.getParameter("phone2"));
		member.setPhone3(request.getParameter("phone3"));
		member.setPost(request.getParameter("post"));
		member.setAddress(request.getParameter("address"));
		member.setGender(request.getParameter("gender"));
		String h="";
		String[] h1 = request.getParameterValues("hobby");
		for(String h2 : h1) {
			h += h2+"-";
		}
		member.setHobby(h);
		member.setIntro(request.getParameter("intro"));
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO old = dao.getmember(member.getId());
		
		if(old.getPasswd().equals(member.getPasswd())) { //비번 일치시
			int result = dao.update(member);
			System.out.println(result);
			if(result==1)System.out.println("회원수정 성공");
			else System.out.println("수정실패");
		}else {
			out.println("<script>");
			out.println("alert('비밀번호 불일치.');");
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
