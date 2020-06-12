<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${sessionScope.id != null}">
	${sessionScope.id}님 환영합니다. <br><br>
	
	<a href="<%=request.getContextPath() %>/UpdateMember.do">회원정보수정 </a><br><br>
	<a href="<%=request.getContextPath() %>/Logout.do">로그아웃 </a><br><br>
	<a href="<%=request.getContextPath() %>/DeleteMember.do"">회원탈퇴 </a><br><br>
</c:if>

<c:if test="${sessionScope.id == null}">
	${sessionScope.id}님 환영합니다. <br><br>
	
	<a href="<%=request.getContextPath() %>/MemberForm.do">회원가입 </a><br><br>
	<a href="<%=request.getContextPath() %>/LoginForm.do">로그인 </a><br><br>
</c:if>