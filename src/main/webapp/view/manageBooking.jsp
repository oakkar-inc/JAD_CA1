<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="model.ServiceHistory"%>
<%@ page import="model.Status"%>
<%@ page import="model.Service"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Manage Bookings</title>
  <link rel="stylesheet" href="/JAD_CA1/view/style/global.css">
  <link rel="stylesheet" href="/JAD_CA1/view/style/manageBooking.css">
</head>

<body>
    <%@ include file="navbar.jsp" %>
    <%
        if (user == null || user.getRoleId() != 1) {
            response.sendRedirect("logout.jsp");
        }
    %>

    <% List<ServiceHistory> bookingList = (List<ServiceHistory>) request.getAttribute("serviceHistoryList"); %>
    <% List<Status> statusList = (List<Status>) request.getAttribute("statusList"); %>
    <% List<Service> serviceList = (List<Service>) request.getAttribute("serviceList"); %>

    <div class="booking-container">
        <%@ include file="adminSidebar.jsp" %>

        <div class="main-content">
            <!-- Filters Section -->

            <!-- Name/Email Filter -->
            <form method="GET" action="manageBooking" class="filters">
                <!-- Name/Email Filter -->
                <div class="filter-item">
                    <input type="text" name="nameEmailFilter" id="nameEmailFilter" placeholder="Search by Name or Email">
                </div>
            
                <!-- Service Filter -->
                <div class="filter-item">
                    <select name="serviceFilter" id="serviceFilter">
                        <option value="">All Services</option>
                        <% for (Service service : serviceList) { %>
                            <option value="<%= service.getName() %>"><%= service.getName() %></option>
                        <% } %>
                    </select>
                </div>
            
                <!-- Status Filter -->
                <div class="filter-item">
                    <select name="statusFilter" id="statusFilter">
                        <option value="">All Statuses</option>
                        <% for (Status status : statusList) { %>
                            <option value="<%= status.getStatusName() %>"><%= status.getStatusName() %></option>
                        <% } %>
                    </select>
                </div>
            
                <!-- Date Filter -->
                <div class="filter-item">
                    <select name="dateFilterOption" id="dateFilterOption" onchange="toggleDateFilters()">
                        <option value="">Select Date Filter</option>
                        <option value="date">Specific Date</option>
                        <option value="period">Period</option>
                    </select>
                </div>
            
                <!-- Specific Date Filter -->
                <div class="filter-item" id="dateFilter" style="display:none;">
                    <input type="date" name="specificDate" id="specificDate" placeholder="Select a Date">
                </div>
            
                <!-- Period Date Filter -->
                <div class="filter-item" id="periodFilter" style="display:none;">
                    <div id="dateRange">
                        <p>From</p>
                        <input type="date" name="startDate" id="startDate" placeholder="Start Date">
                        <p>To</p>
                        <input type="date" name="endDate" id="endDate" placeholder="End Date">
                    </div>
                </div>
            
                <!-- Apply Filters Button -->
                <div class="filter-item">
                    <button type="submit" class="filter-btn">Filter</button>
                </div>
            </form>

            <!-- Bookings Table -->
            <table>
                <thead>
                    <tr>
                        <th>Booking Date</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Service</th>
                        <th>Service Date</th>
                        <th>Price</th>
                        <th>Address</th>
                        <th>Note</th>
                        <th>Feedback</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (bookingList != null && !bookingList.isEmpty()) {
                        for (ServiceHistory booking : bookingList) { %>
                    <tr>
                        <td><%= booking.getBookingDate() %></td>
                        <td><%= booking.getBookingName() %></td>
                        <td><%= booking.getEmail() %></td>
                        <td><%= booking.getServiceName() %></td>
                        <td><%= booking.getServiceDate() %></td>
                        <td><%= "S$" + booking.getPrice() %></td>
                        <td><%= booking.getAddress() %></td>
                        <td><%= booking.getNote() %></td>
                        <td>
                            <%= (booking.getFeedback() != null && !booking.getFeedback().isEmpty()) ? booking.getFeedback() : "" %>
                        </td>
                        <td>
                            <form method="post" action="manageBooking" class="updateStatus" onsubmit="return confirmUpdateStatus(this)">
                                <input type="hidden" name="appointmentId" value="<%= booking.getAppointmentId() %>">
                                <input type="hidden" name="statusId" id="statusId" value="<%= booking.getStatusId() %>">
                                <select name="status" class="status-dropdown" onchange="setStatusId(this)" data-default="<%= booking.getStatusId() %>">
                                    <% for (Status status : statusList) { %>
                                        <option value="<%= status.getStatusId() %>" data-id="<%= status.getStatusId() %>"
                                            <%= status.getStatusName().equalsIgnoreCase(booking.getStatus()) ? "selected" : "" %>>
                                            <%= status.getStatusName() %>
                                        </option>
                                    <% } %>
                                </select>
                                <button type="submit" class="update-btn" style="display: none;">Update</button>
                            </form>
                            
                        </td>
                    </tr>
                    <% } } else { %>
                    <tr>
                        <td colspan="9" class="no-records">No booking found.</td>
                    </tr>
                    <% } %>
                </tbody>
            </table>

        </div>
    </div>

    <script src="/JAD_CA1/view/script/manageBooking.js"></script>
</body>
</html>
