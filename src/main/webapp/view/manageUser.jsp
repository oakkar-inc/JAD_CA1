<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="model.Address"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>User Management Dashboard</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/view/style/global.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/view/style/profile.css">
</head>

<body>
	<%@ include file="/view/navbar.jsp"%>
	<%
	if(user == null || user.getRoleId() != 1) {
		response.sendRedirect("logout.jsp");
	}
	%>
		
	<%
	List<User> userList = (List<User>) request.getAttribute("userList");
	%>
	<div class="profile-container">
		<%@ include file="/view/adminSidebar.html" %>
		<div class="main-content">
		<div>
			<a href="<%=request.getContextPath()%>/view/addUser.jsp"><button style="margin: 2rem;" class="form-button secondary-bg">Add new user</button></a>
			<input id="byAdmin" value="true" hidden>
			<%
			for (User curUser : userList) {
				List<Address> addressList = curUser.getAddresses();
			%>
			<div class="details-container h3">
				<div>
					<form action="<%=request.getContextPath()%>/UserInfo" method="post">
						<input hidden name="userId" value=<%=curUser.getId()%>>
						<input hidden name="roleId" value=<%=curUser.getRoleId()%>>
						<input hidden name="byAdmin" value="true">
						<div class="edit-btn-container">
							<label for="name">Name<br></label>
							<button class="delete-button-user delete-button" data-user-id=<%=curUser.getId() %> type="button">Delete</button>
						</div>
						<input class="form-input" type="text" id="name" name="name" value="<%=curUser.getName()%>" > 
						<label for="mobile">Mobile<br></label>
						<input class="form-input" type="text" id="mobile" name="mobile" value=<%=curUser.getMobile()%> >
						<label for="email">Email<br></label>
						<input class="form-input" type="email" id="email" name="email" value=<%=curUser.getEmail()%> >
						<label for="password" id="passwordLabel">Password<br></label>
						<input class="form-input" type="password" id="password" name="password" value=<%=curUser.getPassword()%>> 
						<input class="form-button" id="saveInfoBtn" name="submit" type="submit" value="Save">
					</form>
				</div>
				<div>
					<%
					for (int i = 0; i < addressList.size(); i++) {
						int addressNo = i + 1;
						Address address = addressList.get(i);
						String addressStr = address.toString();
						out.println("<div class='edit-btn-container'>");
						out.println("<label for='address-" + addressNo + "'>Address " + addressNo + "<br></label>");
						out.println("<a href="+request.getContextPath()+"'/view/editAddress.jsp?addressId=" + address.getId() + "&postal=" + address.getPostal() + "&floor="
						+ address.getFloor() + "&unit=" + address.getUnit()
						+ "&byAdmin=true" + "'><button class='edit-button' type='button'>Edit</button></a>");
						out.println("<button class='delete-button-address delete-button' data-address-id='" + address.getId() + "' type='button'>Delete</button>");
						out.println("</div>");
						out.println("<input class='form-input' type='text' id='address-" + addressNo + "' name='address-" + addressNo
						+ "' value='" + addressStr + "' readonly>");
					}
					%>
					<a href=<%= "addAddress.jsp?userId=" +  curUser.getId()%>>
					<button class="form-button secondary-bg" class="addAddressBtns">+ Add new address</button>
					</a>
					<br>
				</div>
			</div>
		<hr>
		<%
		}
		%>
		</div>
	</div>
	</div>
	<script src="script/profile.js"></script>
	<script src="script/manageUser.js"></script>
</body>
</html>