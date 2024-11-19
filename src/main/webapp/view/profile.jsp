<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="model.User" %>
<%@ page import="model.Address" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
    <link rel="stylesheet" href="style/global.css">
    <link rel="stylesheet" href="style/profile.css">
</head>

<body>

<%
	User user = (User) session.getAttribute("user");
	if (user == null) {
		response.sendRedirect("login.jsp");
	} else {
		
	List<Address> addressList = (List<Address>) session.getAttribute("addressList");
	
%>

    <div class="navbar"> just a placeholder</div>
    
    <div class="profile-container">
        <div class="sidebar-container">
            <div class="sidebar">
                <div class="sidebar-nav h3">
                    <a href="profile.html">
                        <div class="sidebar-navitem">
                            <img src="./assets/user-square.png">
                            <p>
                                Account Details
                            </p>
                        </div>
                    </a>
                    <a href="history.html">
                        <div class="sidebar-navitem">
                            <img src="./assets/refresh-left-square.png">
                            <p>
                                Service History
                            </p>
                        </div>
                    </a>
                    <a href="logut.jsp">
                        <div class="sidebar-navitem">
                            <img src="./assets/logout.png">
                            <p>
                                Log Out
                            </p>
                        </div>
                    </a>
                </div>
            </div>
        </div>
        <div class="main-content">
            <div class="details-container h3">
                <div>
                    <label for="name">Name<br></label>
                    <input class="form-input" type="text" id="name" name="name" value=<%=user.getFirstName() + " " + user.getLastName()%> readonly>
                    <label for="mobile">Mobile<br></label>
                    <input class="form-input" type="text" id="mobile" name="mobile" value=<%=user.getMobile() %> readonly>
                    <label for="email">Email<br></label>
                    <input class="form-input" type="email" id="email" name="email" value=<%=user.getEmail() %> readonly>
                    
                </div>
                <div>
                	<% 
                	for(int i=0; i < addressList.size(); i++) {
                		int addressNo = i+1;
                		Address address = addressList.get(i);
                		String addressStr = address.toString();
                		out.println("<label for='address-" + addressNo + "'>Address " + addressNo + "<br></label>");
                		out.println("<input class='form-input' type='text' id='address-" + addressNo + "' name='address-" + addressNo + "' value='" + addressStr + "' readonly>");
                	}
                	%>
                    <button class="form-button secondary-bg" id="addAddressBtn">+ Add new address</button><br>
                    <form action="/JAD_CA1/api/address" method="post" id="newAddressForm" hidden>
                        <label class="h2" for="password">New Address<br></label>
                        <input hidden name="userId" value=<%=user.getId() %>>
                        <label for="postal">Postal Code<br></label>
                        <input class="form-input" type="number" id="postal" name="postal" required>
                        <label for="floor">Floor<br></label>
                        <input class="form-input" type="number" id="floor" name="floor" required>
                        <label for="unit">Unit<br></label>
                        <input class="form-input" type="number" id="unit" name="unit" required>
                        <input class="form-button text-danger" id="cancelBtn" name="submit" type="reset" value="Cancel">
                        <input class="form-button secondary-bg" name="submit" type="submit" value="Submit">    
                    </form>
                </div>
                
            </div>
        </div>
    </div>
    <script src="script/profile.js"></script>
    <% } %>
</body>
</html>