<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<p>home - just a dummy page to test session</p>
<%
	User user = (User) session.getAttribute("user");
	out.print(user.toString());
%>
</body>
</html>