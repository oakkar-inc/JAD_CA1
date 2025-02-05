<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>  
<%

User user = (User) session.getAttribute("user");
Integer roleId = null;

if (user != null) {
    roleId = user.getRoleId();
}

if(user == null || user.getRoleId() != 1) {
	response.sendRedirect(request.getContextPath() + "/view/logout.jsp");
}
%>