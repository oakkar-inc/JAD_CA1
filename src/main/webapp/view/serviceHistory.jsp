<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="model.ServiceHistory" %>
<%@ include file="navbar.jsp" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Service History</title>
    <link rel="stylesheet" href="style/global.css">
    <link rel="stylesheet" href="style/serviceHistory.css">
</head>

<body>
    <%
        List<ServiceHistory> serviceHistoryList = (List<ServiceHistory>) request.getAttribute("serviceHistoryList");
    %>

    <div class="history-container">
        <div class="sidebar-container">
            <div class="sidebar">
                <div class="sidebar-nav h3">
                    <a href="profile.jsp">
                        <div class="sidebar-navitem">
                            <img src="./assets/user-square.png">
                            <p>Account Details</p>
                        </div>
                    </a>
                    <a href="serviceHistory">
                        <div class="sidebar-navitem">
                            <img src="./assets/refresh-left-square.png">
                            <p>Service History</p>
                        </div>
                    </a>
                    <a href="logout.jsp">
                        <div class="sidebar-navitem">
                            <img src="./assets/logout.png">
                            <p>Log Out</p>
                        </div>
                    </a>
                </div>
            </div>
        </div>

        <div class="main-content">
            <table>
                <thead>
                    <tr>
                        <th>Booked Date</th>
                        <th>Name</th>
                        <th>Service</th>
                        <th>Price</th>
                        <th>Address</th>
                        <th>Note</th>
                        <th>Status</th>
                        <th>Feedback</th>
                        <th></th>
						<th></th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if (serviceHistoryList != null && !serviceHistoryList.isEmpty()) {
                            for (ServiceHistory history : serviceHistoryList) {
                    %>
                    <tr>
                        <td><%= history.getDate() %></td>
                        <td><%= history.getBookingName() %></td>
                        <td><%= history.getServiceName() %></td>
                        <td><%= "$" + history.getPrice() %></td>
                        <td><%= history.getAddress() %></td>
                        <td class="note-column"><%= history.getNote() %></td>
                        <td>
                            <button class="status-<%= history.getStatus().split(" ")[0].toLowerCase() %>">
                                <%= history.getStatus() %>
                            </button>                            
                        </td>
                        <td class="feedback-column">
                            <%
                                if (history.getFeedback() != null && !history.getFeedback().isEmpty()) {
                            %>
                                <%= history.getFeedback() %>
                            <%
                                } else {
                                    if ("completed".equalsIgnoreCase(history.getStatus())) {
                            %>
                                <form method="post" action="serviceHistory" class="feedback-form">
                                    <input type="hidden" name="appointmentId" value="<%= history.getAppointmentId() %>">
                                    <input type="hidden" name="action" value="feedback">
                                    <textarea name="feedback" placeholder="Add Feedback"></textarea>
                                    <button type="submit" class="feedback-submit">Submit</button>
                                </form>
                            <%	
                                    }
                                }
                            %>
                        </td>
                        <td>
                            <%
                                if ("payment pending".equalsIgnoreCase(history.getStatus())) {
                            %>
                                <form method="post" action="serviceHistory">
                                    <input type="hidden" name="appointmentId" value="<%= history.getAppointmentId() %>">
                                    <input type="hidden" name="action" value="payment">
                                    <button type="submit" class="pay-btn">Pay</button>
                                </form>
                            <%
                                }
                            %>
                        </td>
                        <td>
                            <%
                                if (!"completed".equalsIgnoreCase(history.getStatus()) && !"canceled".equalsIgnoreCase(history.getStatus())) {
                            %>
                                <form method="post" action="serviceHistory">
                                    <input type="hidden" name="appointmentId" value="<%= history.getAppointmentId() %>">
                                    <input type="hidden" name="action" value="cancel">
                                    <button type="submit" class="cancel-btn">Cancel</button>
                                </form>
                            <%
                                }
                            %>
                        </td>            
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="9" class="no-records">No service history found.</td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>
</body>

</html>
