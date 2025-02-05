<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="model.Category, model.Service"%>	


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
		response.sendRedirect("/JAD_CA1/view/logout.jsp");
	}
	%>

	<%
	List<Category> categoryList = (List<Category>) request.getAttribute("categoryList");
	Category category = (Category) request.getAttribute("category");
	List<Service> serviceList = (List<Service>) request.getAttribute("serviceList");
	%>
	<div class="profile-container">
		<%@ include file="adminSidebar.jsp"%>
		<div class="main-content">
		<a href=<%= "../addService.jsp?cid=" + category.getCategoryId() %>><button style="margin: 2rem;" class="form-button secondary-bg">Add new service</button></a>
			<form>
                <div class="details-container h3">
                    <div>
                    	<div class="edit-btn-container">
							<label for="name">Category Name<br></label>
							<button class="delete-button" id="delCatBtn" data-category-id=<%=category.getCategoryId() %> type="button">Delete</button>
						</div>
                        
                        <input class="form-input" type="text" id="catName" name="name" value="<%=category.getName() %>">
                        <label for="catImgUrl">Image URL<br></label>
                        <input class="form-input" type="text" id="catImgUrl" name="imgUrl" value=<%=category.getImage_url() %>>
                        <input class="form-button saveCatInfoBtn" id="catSave" data-cat-id=<%=category.getCategoryId() %> type="button" value="Save">
                    </div>
                    <div>
                        <label for="address-1">Description<br></label>
                        <textarea name="description" id="catDescription" class="form-input-long h3"><%=category.getDescription() %></textarea>
                    </div>
                </div>
            </form>
            <hr>
            <% if (serviceList != null) {
            	for (Service service : serviceList) { %>
            
            <form>
                <div class="details-container h3">
                    <div>
              			<div class="edit-btn-container">
							<label for="name">Service Name<br></label>
							<button class="delete-button-service delete-button" data-service-id=<%=service.getServiceId() %> type="button">Delete</button>
						</div>
                        
                        <input class="form-input" type="text" name="name" value="<%=service.getName() %>">
                        <label for="imgUrl">Image URL<br></label>
                        <input class="form-input" type="text" name="imgUrl" value="<%=service.getImage_url() %>">
                        <div class="service-price-container">
                            <div>
                                <label for="price">Price<br></label>
                                <input class="form-input" name="price" type="text" value="<%=service.getPrice() %>">
                            </div>
                            <div>
                                <label for="categories">Category<br></label>
                                <select class="form-input" name="categoryId">
                                	<% for (Category cat : categoryList) {%>
                                    <option value=<%=cat.getCategoryId() %> <%=cat.getCategoryId() == service.getCategoryId() ? "selected" : "" %>>
                                    <%=cat.getName() %>
                                    </option>
                                    <% } %>
                                </select>
                            </div>
                        </div>
                        <input class="form-button saveInfoBtn" data-service-id=<%=service.getServiceId() %> name="submit" type="button" value="Save">
                    </div>
                    <div>
                        <label for="details">Details<br></label>
                        <textarea name="details"
                            class="form-input-long h3"><%=service.getDetails() %></textarea>
                    </div>

                </div>
            </form>
            <hr>
            <% } }%>
		</div>
	</div>
	<script src="/JAD_CA1/view/script/editCategory.js"></script>
</body>
</html>