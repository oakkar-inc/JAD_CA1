<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="model.ServiceReport"%>
<%@ page import="model.CustomerReport"%>
<%@ page import="com.google.gson.Gson"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reports</title>
    <link rel="stylesheet" href="/JAD_CA1/view/style/global.css">
    <link rel="stylesheet" href="/JAD_CA1/view/style/report.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <%@ include file="navbar.jsp" %>

    <% 
        // Verifying user role (admin)
        if (user == null || user.getRoleId() != 1) {
            response.sendRedirect("logout.jsp");
        }
    %>

    <div class="report-container">
        <%@ include file="adminSidebar.jsp" %>

        <div class="main-content">
            <% List<CustomerReport> topCustomers = (List<CustomerReport>) request.getAttribute("topCustomers"); %>
            <% List<ServiceReport> mostDemandedServices = (List<ServiceReport>) request.getAttribute("mostDemandedServices"); %>
            
            <div id="topcustomers">
                <h3 class="h3">Top 10 Customers by Total Booking Value</h3>
                <table class="report-table">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Total Spent ($)</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% int count = 1; %>
                        <% for (CustomerReport customer : topCustomers) { %>
                            <tr>
                                <td><%= count++ %></td>
                                <td><%= customer.getName() %></td>
                                <td><%= customer.getEmail() %></td>
                                <td>$<%= customer.getTotalSpent() %></td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
            
            <div id="topservices">
                <h3 class="h3">Most Demanded Cleaning Services</h3>
                <table class="report-table">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Service Name</th>
                            <th>Bookings</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% count = 1; %>
                        <% for (ServiceReport service : mostDemandedServices) { %>
                            <tr>
                                <td><%= count++ %></td>
                                <td><%= service.getName() %></td>
                                <td><%= service.getBookingCount() %></td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>

            <%
                List<Map<String, Object>> earningsData = (List<Map<String, Object>>) request.getAttribute("monthlyEarnings");
                List<String> months = new ArrayList<>();
                List<Double> earnings = new ArrayList<>();

                for (Map<String, Object> data : earningsData) {
                    months.add(data.get("month") + "/" + data.get("year"));
                    earnings.add((Double) data.get("total_earned"));
                }
                
                // Convert months and earnings to JSON format using Gson
                Gson gson = new Gson();
                String monthsJson = gson.toJson(months);
                String earningsJson = gson.toJson(earnings);
            %>

            <div id="salesChart">
                <h3 class="h3">Sales per Month</h3>
                <canvas id="earningsChart" width="400" height="200"></canvas>

                <script>
                    var months = <%= monthsJson %>;  // Correct JSON format for months
                    var earnings = <%= earningsJson %>;  // Correct JSON format for earnings

                    var ctx = document.getElementById('earningsChart').getContext('2d');
                    var earningsChart = new Chart(ctx, {
                        type: 'line',
                        data: {
                            labels: months, // Labels from the backend (months)
                            datasets: [{
                                label: 'Total Money Earned ($)',
                                data: earnings, // Earnings from the backend
                                borderColor: 'rgba(75, 192, 192, 1)',
                                borderWidth: 2,
                                fill: false
                            }]
                        },
                        options: {
                            responsive: true,
                            scales: {
                                x: {
                                    title: {
                                        display: true,
                                        text: 'Month/Year'
                                    }
                                },
                                y: {
                                    title: {
                                        display: true,
                                        text: 'Total Earned ($)'
                                    },
                                    beginAtZero: true
                                }
                            }
                        }
                    });
                </script>
            </div>

        </div>
    </div>
</body>
</html>
