package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import model.MemberDTO;

public class MemberInsert implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("MemberInsert");
		
		request.setCharacterEncoding("utf-8");
		
		MemberDTO dto = new MemberDTO();
		dto.setId(request.getParameter("id"));
		dto.setPasswd(request.getParameter("passwd"));
		dto.setName(request.getParameter("name"));
		dto.setJumin1(request.getParameter("jumin1"));
		dto.setJumin2(request.getParameter("jumin2"));
		dto.setMailid(request.getParameter("mailid"));
		dto.setDomain(request.getParameter("domain"));
		dto.setTel1(request.getParameter("tel1"));
		dto.setTel2(request.getParameter("tel2"));
		dto.setTel3(request.getParameter("tel3"));
		dto.setPhone1(request.getParameter("phone1"));
		dto.setPhone2(request.getParameter("phone2"));
		dto.setPhone3(request.getParameter("phone3"));
		dto.setPost(request.getParameter("post"));
		dto.setAddress(request.getParameter("address"));
		dto.setGender(request.getParameter("gender"));

		String h = "";
		String[] h1 = request.getParameterValues("hobby");
		for(String h2 : h1) {
			h += h2 + "-";
		}
		dto.setHobby(h);
		
		dto.setIntro(request.getParameter("intro"));
		
		MemberDAO dao = new MemberDAO();
		int result = dao.insert(dto);
		if(result==1) {
			System.out.println("회원가입 성공");
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/member/loginform.jsp");
		return forward;
	}

}
