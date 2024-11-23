<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add service</title>
 <link rel="stylesheet" href="style/global.css">
 <link rel="stylesheet" href="style/editAddress.css">
</head>
<body class="secondary-bg">
<%@ include file="adminCheck.jsp" %>
<%
	int categoryId = Integer.parseInt(request.getParameter("cid"));
%>

<div class="container">
        <div class="form-container primary-bg">
            <form action="/JAD_CA1/view/service" method="post" class="h3">
                <p class="h1">Add Service</p>
                <input hidden name="categoryId" id="categoryId" value=<%= categoryId%>>
                <label for="name">Service Name<br></label>
                <input class="form-input" type="text" id="name" name="name" required>
                <label for="image_url">Image URL<br></label>
                <input class="form-input" type="text" id="image_url" name="image_url" required>
                <label for="price">Price<br></label>
                <input class="form-input" type="text" id="price" name="price" required>
                <label for="details">Details<br></label>
                <textarea class="form-input-long" id="details" name="details" required></textarea>
                <br>
                <a href="manageCategory">
                <input class="form-button text-danger" id="cancelBtn" type="button" value="Cancel">
                </a>
                <input class="form-button secondary-bg" id="updateBtn" type="submit" value="Add">
            </form>
        </div>
    </div>
</body>
</html>