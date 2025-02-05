<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="model.Category"%>	

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Category Management Dashboard</title>
<link rel="stylesheet" href="/JAD_CA1/view/style/global.css">
<link rel="stylesheet" href="/JAD_CA1/view/style/profile.css">
<link rel="stylesheet" href="/JAD_CA1/view/style/manageCategory.css">
</head>

<body>
	<%@ include file="navbar.jsp"%>
	<%
	if (user == null || user.getRoleId() != 1) {
		response.sendRedirect("logout.jsp");
	}
	%>

	<%
	List<Category> categoryList = (List<Category>) request.getAttribute("categoryList");
	%>
	<div class="profile-container">
		<%@ include file="adminSidebar.html"%>
		<div class="main-content">
		
			<div class="card-container h2">
				<% for (Category category : categoryList) { %>
					<div class="card cat-card" data-category-id=<%=category.getCategoryId()%>>
						<%=category.getName() %>
					</div>
				<% } %>
				<a href="addCategory.jsp">
				<div class="card">
                   + Add new
                </div>
                </a>
			</div>
		</div>
	</div>
	<script src="/JAD_CA1/view/script/manageCategory.js"></script>
</body>
</html>