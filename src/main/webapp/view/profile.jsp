<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="model.User"%>
<%@ page import="model.Address"%>
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

	<%@ include file="navbar.jsp"%>
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
					</a> <a href="serviceHistory">
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
		<div class="main-content">
			<div class="details-container h3">
				<div>
					<form action="/JAD_CA1/api/userinfo" method="post">
						<input hidden name="userId" value=<%=user.getId()%>> <input
							hidden name="roleId" value=<%=user.getRoleId()%>>
						<div class="edit-btn-container">
							<label for="name">Name<br></label>
							<button class="edit-button" id="editInfoBtn" type="button">Edit
								Info</button>
							<a href="profile.jsp" id="editCancelBtn" hidden>
								<button class="edit-button" type="button">Cancel</button>
							</a>
						</div>
						<input class="form-input" type="text" id="name" name="name"
							value="<%=user.getName()%>" readonly> <label for="mobile">Mobile<br></label>
						<input class="form-input" type="text" id="mobile" name="mobile"
							value=<%=user.getMobile()%> readonly> <label for="email">Email<br></label>
						<input class="form-input" type="email" id="email" name="email"
							value=<%=user.getEmail()%> readonly> <label
							for="password" id="passwordLabel" hidden>Password<br></label>
						<input class="form-input" type="password" id="password"
							name="password" value=<%=user.getPassword()%> hidden> <input
							class="form-button" id="saveInfoBtn" name="submit" type="submit"
							value="Save" hidden>
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
						out.println("<a href='editAddress.jsp?addressId=" + address.getId() + "&postal=" + address.getPostal() + "&floor="
						+ address.getFloor() + "&unit=" + address.getUnit()
						+ "'><button class='edit-button' type='button'>Edit</button></a>");
						out.println("<button class='delete-button' address-id='" + address.getId() + "' type='button'>Delete</button>");
						out.println("</div>");
						out.println("<input class='form-input' type='text' id='address-" + addressNo + "' name='address-" + addressNo
						+ "' value='" + addressStr + "' readonly>");
					}
					%>
					<button class="form-button secondary-bg" id="addAddressBtn">+
						Add new address</button>
					<br>
					<form action="/JAD_CA1/api/address" method="post"
						id="newAddressForm" hidden>
						<label class="h2" for="password">New Address<br></label>
						<input hidden name="userId" value=<%=user.getId()%>>
						<input hidden name="byAdmin" value="false">
						<label
							for="postal">Postal Code<br></label> <input
							class="form-input" type="number" id="postal" name="postal"
							required> <label for="floor">Floor<br></label> <input
							class="form-input" type="number" id="floor" name="floor" required>
						<label for="unit">Unit<br></label> <input class="form-input"
							type="number" id="unit" name="unit" required> <input
							class="form-button text-danger" id="cancelBtn" name="submit"
							type="reset" value="Cancel"> <input
							class="form-button secondary-bg" name="submit" type="submit"
							value="Submit">
					</form>
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