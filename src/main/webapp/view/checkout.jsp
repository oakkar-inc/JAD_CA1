<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.ServiceDAO, model.Service" %>
<%@ page import="java.sql.SQLException" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Checkout</title>
    
    <!-- Styles -->
    <link rel="stylesheet" href="style/global.css">
    <link rel="stylesheet" href="style/checkout.css">

    <!-- Stripe.js -->
    <script src="https://js.stripe.com/v3/"></script>
</head>
<body>

    <%
        int serviceId = Integer.parseInt(request.getParameter("serviceId"));
        int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));

        ServiceDAO serviceDAO = new ServiceDAO();
        Service service = null;

        try {
            service = serviceDAO.getServiceByServiceId(serviceId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (service != null) {
            String serviceName = service.getName();
            String serviceDetails = service.getDetails();
            String serviceImage = service.getImage_url();
            double price = service.getPrice();
            double gst = price * 0.09; // 9% GST
            double totalPrice = price + gst;
            
            // Retrieve the Stripe Publishable Key
            String publishableKey = (String) request.getAttribute("publishableKey");
    %>
    <main>
        <div class="checkout-container">
            <h2>Checkout</h2>
            
            <div class="service-details">
                <img src="<%= service.getImage_url() %>" alt="Service Image" class="service-image">
                <h3 class="h3 service-title"><%= service.getName() %></h3>
          <p class="text-body description"><%= service.getDetails() %></p>
                <table class="service-price-container">
                    <tr><td>Price:</td><td>$<%= String.format("%.2f", price) %></td></tr>
                    <tr><td>GST (9%):</td><td>$<%= String.format("%.2f", gst) %></td></tr>
                    <tr class="total-price"><td><strong>Total Price:</strong></td><td><strong>$<%= String.format("%.2f", totalPrice) %></strong></td></tr>
                </table>
                
            </div>

            <button id="checkout-button" class="checkout-button">Pay Now</button>
        </div>
    </main>
    
    <script>
        var stripe = Stripe("<%= publishableKey %>");
        var checkoutButton = document.getElementById("checkout-button");

        checkoutButton.addEventListener("click", function () {
            fetch("/JAD_CA1/view/checkout", {
                method: "POST",
                headers: { "Content-Type": "application/x-www-form-urlencoded" },
                body: `appointmentId=<%= appointmentId %>&serviceId=<%= serviceId %>&totalPrice=<%= totalPrice %>&serviceName=<%= serviceName %>&serviceDetails=<%= serviceDetails %>&serviceImage=<%= serviceImage %>`
            })
            .then(response => response.json())
            .then(session => {
                return stripe.redirectToCheckout({ sessionId: session.id });
            })
            .catch(error => console.error("Error:", error));
        });
    </script>

    <%
        } else {
    %>
        <p class="text-danger">Service details not found. Please try again later.</p>
    <%
        }
    %>

</body>
</html>
