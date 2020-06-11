package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;
import model.MemberDTO;

public class UpdateMember implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("UpdateMember");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO dto = dao.getmember(id);
		
		String hobby = dto.getHobby();
		String[] h = hobby.split("-");
		
		request.setAttribute("member", dto);
		request.setAttribute("h", h);
		
		ActionForward actionforward = new ActionForward();
		actionforward.setRedirect(false); //dispatcher방식
		actionforward.setPath("/member/updateform.jsp");
		
		return actionforward;
	}

}
