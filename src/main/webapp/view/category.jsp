<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.List" %>
<%@ page import="model.Category" %>
<%@ include file="navbar.jsp" %>  
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style/category.css">
    <link rel="stylesheet" href="style/global.css">
    <title>Category</title>
</head>
<body>
    <h1 class="h1">We take pride in our services</h1>
    <div class="cards">
        <% 
        	@SuppressWarnings("unchecked")
            // Retrieve the category list from the request scope
			List<Category> categoryList = (List<Category>) request.getAttribute("categoryList");
	        
            if (categoryList != null && !categoryList.isEmpty()) {
                for (model.Category category : categoryList) {
        %>
                    <div class="card">
                        <div class="card-top">
                            <div class="card-text">
                                <h2 class="h2"><%= category.getName() %></h2>
                                <p class="text-body"><%= category.getDescription() %></p>
                            </div>
                            <div class="card-button">
                                <a href="service?catId=<%= category.getCategoryId() %>">
								    <img src="assets/arrow.png" alt="detail">
								</a>
                            </div>
                        </div>
                        <div class="card-img">
                            <img src="<%= category.getImage_url() %>" alt="<%= category.getName() %>">
                        </div>
                    </div>
        <% 
                }
            } else {
        %>
                <p>No categories available at the moment.</p>
        <% 
            }
        %>
    </div>
</body>
</html>
