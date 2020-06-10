package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;

public class UpdateMember implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("UpdateMember");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		MemberDAO dao = new MemberDAO();
		int result = dao.memberUpdate(id);
		
		ActionForward actionforward = new ActionForward();
		
		return actionforward;
	}

}
