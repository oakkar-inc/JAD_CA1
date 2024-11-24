<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="model.User"%>
<%@ page import="model.Address"%>
<%@ include file="navbar.jsp"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Profile</title>
<link rel="stylesheet" href="style/global.css">
<link rel="stylesheet" href="style/profile.css">
</head>

<body>
	<%
	if (user == null) {
		response.sendRedirect("login.jsp");
	} else {

		List<Address> addressList = user.getAddresses();
	%>
	<div class="profile-container">
		<div class="sidebar-container">
			<div class="sidebar">
				<div class="sidebar-nav h3">
					<a href="profile.jsp">
						<div class="sidebar-navitem">
							<img src="./assets/user-square.png">
							<p>Account Details</p>
						</div>
					</a> <a href="serviceHistory.jsp">
						<div class="sidebar-navitem">
							<img src="./assets/refresh-left-square.png">
							<p>Service History</p>
						</div>
					</a> <a href="logout.jsp">
						<div class="sidebar-navitem">
							<img src="./assets/logout.png">
							<p>Log Out</p>
						</div>
					</a>
				</div>
			</div>
		</div>

		
	</div>
	<script src="script/profile.js"></script>
	<%
	}
	%>
</body>
</html>