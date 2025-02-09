<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>  
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Sparkle</title>
	<link rel="stylesheet" href="/JAD_CA1/view/style/navbar.css">
	<link rel="stylesheet" href="/JAD_CA1/view/style/global.css">
</head>
<body>
    <%
        User user = (User) session.getAttribute("user");
        Integer roleId = null;

        if (user != null) {
            roleId = user.getRoleId();
        }
    %>

    <nav class="primary-bg nav-bar">
        <a href="<%=request.getContextPath()%>/view/home.jsp">
    		<img src="<%=request.getContextPath()%>/view/assets/logo.png" alt="logo">
		</a>
        <div class="tab-list">
            <ul id="gp-1" class="nav-tab">
                <li><a href="<%=request.getContextPath()%>/view/category">Services</a></li>
                <% if (user != null && roleId != null) { %>
                    <% if (roleId == 1) { %>
                        <li><a href="<%=request.getContextPath()%>/ManageUser">Dashboard</a></li>
                    <% } %>
                <% } %>
            </ul>
            <ul id="gp-2" class="nav-tab">
                <% if (user == null) { %>
                    <li><a href="<%=request.getContextPath()%>/view/login.jsp">Login</a></li>
                    <li><a href="<%=request.getContextPath()%>/view/register.jsp">Sign Up</a></li>
                <% } else { %>
                    <% if (roleId == 1 || roleId == 2) { %>
                        <li><a href="<%=request.getContextPath()%>/view/profile.jsp">Profile</a></li>
                    <% } %>
                <% } %>
            </ul>
        </div>
    </nav>
</body>
</html>
