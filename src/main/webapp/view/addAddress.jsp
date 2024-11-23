<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add address</title>
    <link rel="stylesheet" href="style/global.css">
    <link rel="stylesheet" href="style/editAddress.css">
</head>
<body class="secondary-bg">
<%@ include file="adminCheck.jsp" %>
<%
	int userId = Integer.parseInt(request.getParameter("userId"));
%>
    <div class="container">
        <div class="form-container primary-bg">
            <form action="/JAD_CA1/api/address" method="post" class="h3">
                <p class="h1">Add Address</p>
                <input hidden name="userId" id="userId" value=<%= userId%>>
                <input hidden name="byAdmin" id="userId" value="true">
                <label for="postal">Postal Code<br></label>
                <input class="form-input" type="number" id="postal" name="postal" required>
                <label for="floor">Floor<br></label>
                <input class="form-input" type="number" id="floor" name="floor" required>
                <label for="unit">Unit<br></label>
                <input class="form-input" type="number" id="unit" name="unit" required>
                <input class="form-button text-danger" id="cancelBtn" type="button" value="Cancel">
                <input class="form-button secondary-bg" id="updateBtn" type="submit" value="Add">
            </form>
        </div>
    </div>
</body>
</html>