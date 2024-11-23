<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="model.Service"%>
<%@ page import="model.Category"%>
<%@ page import="model.User"%>
<%@ include file="navbar.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Service</title>
<link rel="stylesheet" href="style/global.css">
<link rel="stylesheet" href="style/service.css">
</head>
<body>
    <div class="container">
        <main> 
            <div class="category">
            <% 
                Category category = (Category) request.getAttribute("category");
                if (category != null) {
            %>
                <h1 class="h1">
                    <%= category.getName() %>
                </h1>
                <p class="text-body">
                    <%= category.getDescription() %>
                </p>
            <% 
                } else {
            %>
                <h1 class="h1">No Category Selected</h1>
            <% 
                }
            %>
            </div>
            <div class="service">
                <h2 class="h2">Choose your service</h2>
                <p id="errorMessage" class="error-message text-body">Please select a service to book.</p>
                <div class="cards">
                    <%
                        List<Service> serviceList = (List<Service>) request.getAttribute("serviceList");
                        if (serviceList != null && !serviceList.isEmpty()) {
                            for (Service service : serviceList) {
                    %>
						<div class="card" id="<%=service.getServiceId() %>">
						    <div class="card-img">
						        <img src="<%= service.getImage_url() %>" alt="<%= service.getName() %>">
						    </div>
						    <div class="card-text">
						        <h3 class="h3"><%= service.getName() %></h3>
						        <p class="text-body">
						            <%= service.getDetails() %>
						        </p>
						    </div>
						    <div class="card-footer">
						        <p class="text-cta price">$<%= service.getPrice() %></p>
						    </div>
						</div>
                    <% 
                            }
                        } else { 
                    %>
                    <p>No services available for this category.</p>
                    <% } %>
                </div>
            </div>
            <div class="btn">
				<a id="bookNowButton" href="#" class="btn-primary text-cta" 
				   data-category-id="<%= category.getCategoryId() %>" 
				   data-user-id="<%= user != null ? user : "NoLogin" %>">
				    Book Now
				</a>
            </div>
        </main>
        <% if (category != null) { %>
            <div class="img-div">
                <img src="<%= category.getImage_url() %>" alt="Category Image">
            </div>
        <% } %>     
    </div>
    <script src="script/service.js"></script>
</body>
</html>
