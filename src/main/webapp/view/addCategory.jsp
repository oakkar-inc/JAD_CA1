<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Category</title>
 <link rel="stylesheet" href="style/global.css">
 <link rel="stylesheet" href="style/editAddress.css">
</head>
</head>
<%@ include file="adminCheck.jsp" %>
<body class="secondary-bg">
    <div class="container">
        <div class="form-container primary-bg">
            <form action="/JAD_CA1/view/category" method="post" class="h3">
                <p class="h1">Add Category</p>
                <label for="name">Category Name<br></label>
                <input class="form-input" type="text" id="name" name="name" required>
                <label for="image_url">Image URL<br></label>
                <input class="form-input" type="text" id="image_url" name="image_url" required>
                <label for="description">Description<br></label>
                <textarea class="form-input-long" id="description" name="description" required></textarea>
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