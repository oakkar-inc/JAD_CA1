<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>  
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Nav-Bar</title>
	<link rel="stylesheet" href="style/navbar.css">
	<link rel="stylesheet" href="style/global.css">
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
        <img src="assets/logo.png" alt="logo">
        <div class="tab-list">
            <ul id="gp-1" class="nav-tab">
                <li><a href="services.html">Services</a></li>
                <% if (user != null && roleId != null) { %>
                    <% if (roleId == 1) { %>
                        <li><a href="dashboard.html">Dashboard</a></li>
                    <% } %>
                <% } %>
            </ul>
            <ul id="gp-2" class="nav-tab">
                <% if (user == null) { %>
                    <li><a href="login.html">Login</a></li>
                    <li><a href="register.html">Sign Up</a></li>
                <% } else { %>
                    <% if (roleId == 1 || roleId == 2) { %>
                        <li><a href="profile.html">Profile</a></li>
                    <% } %>
                <% } %>
            </ul>
        </div>
    </nav>
</body>
</html>
