package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Action;
import service.ActionForward;
import service.Idcheck;
import service.MemberInsert;


@WebServlet("*.do")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		
		System.out.println("requestURI : " + requestURI);
		System.out.println("contextPath : " + contextPath);
		System.out.println("command : " + command);
		
		Action action = null;
		ActionForward actionforward = null;
		
		//회원가입
		if(command.equals("/MemberInsert.do")){
			try {
				action = new MemberInsert();
				actionforward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		//회원가입 폼
		}else if(command.equals("/MemberForm.do")){
				try {
					actionforward = new ActionForward();
					actionforward.setRedirect(true);
					actionforward.setPath("./member/memberform.jsp");
				}catch(Exception e) {
					e.printStackTrace();
				}
		//아이디 중복검사
		}else if(command.equals("/Idcheck.do")){
			try {
				action = new Idcheck();
				actionforward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		if(actionforward != null) {
			if(actionforward.isRedirect()) { //redirect 방식으로 포워딩
				response.sendRedirect(actionforward.getPath());
			}else { //dispatcher방식으로 포워딩
				RequestDispatcher dispatcher = request.getRequestDispatcher(actionforward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Method : doGet");
		
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Method : doPost");
		
		doProcess(request, response);
	}

}
