<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import = "model.User" %>
<%@page import = "model.Category"%>
<%@page import = "model.Service"%>
<%@page import = "model.Address"%>
<%@page import = "java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Appointment</title>
<link rel="stylesheet" href="style/global.css">
<link rel="stylesheet" href="style/datePicker.css">
<link rel="stylesheet" href="style/appointment.css">
</head>
<body>
	<%
	User user = (User) session.getAttribute("user");
	Category category = (Category) request.getAttribute("category");
	Service service = (Service) request.getAttribute("service");
    Address address = (Address) request.getAttribute("address");
	%>
    <main>
        <div class="booking-card">
            <h2 class="h2">Booking Details</h2>
            <form action="" method="POST">
                <input type="hidden" name="action" value="bookNow">

                <div class="form-container">
                    <div id="left">
                        <label for="name" class="h3">Name</label>
                        <input type="text" id="name" name="name" class="text-input text-body" value="<%=user.getName()%>" required>

                        <label for="phone" class="h3">Phone</label>
                        <input type="tel" id="phone" name="phone" class="text-input text-body" value="<%=user.getMobile()%>" required>

                        <label for="ser-select" class="h3">Selected Service</label>
                        <input type="text" id="category" name="ser-select" class="text-input text-body" value="<%=category.getName()%>" readonly>
                        <input type="text" id="service" name="ser-select" class="text-input text-body" value="<%=service.getName()%>" readonly>

                        <label class="date-label h3" for="date">Select Date</label>
                        <input type="date" id="date" name="date" class="date-input text-body" required>                        
                    </div>

                    <div id="right">
                        <label for="address-select" class="h3">Select Address</label>
                        <select id="address-select" name="address" class="text-input text-body">
                            <%
                                List<Address> addressList = (List<Address>) request.getAttribute("addressList");
                                boolean isFirst = true; 
                                for (Address a : addressList) {
                                    String selected = isFirst ? "selected" : ""; 
                                    isFirst = false; 
                            %>    
                                <option value="<%= a.getId() %>" <%= selected %>>
                                    <%= "#" + String.format("%02d", a.getFloor()) + "-" + String.format("%03d", a.getUnit()) + "  S" + a.getPostal() %>
                                </option>
                            <%
                                }
                            %>
                        </select>
                        

                        <label for="special-request" class="h3">Special Request</label>
                        <textarea id="special-request" name="special-request" class="text-input text-body" rows="4" placeholder="Enter your request here..."></textarea>                        
                    </div>
                </div>
                
                
                <div class="action-btns">
                    <div class="action-btns">
                        <div class="action-btns">
                            <button type="button" onclick="window.location.href='home.jsp'" class="secondary-bg text-cta btn-danger">Cancel</button>
                            <button type="submit" class="primary-bg text-cta btn-primary">Book Now</button>
                        </div>
                        
                    </div>
                    
                </div>
            </form>
        </div>
    </main>
    <script src="script/datePicker.js"></script>
</body>
</html>