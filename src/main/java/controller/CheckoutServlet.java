package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.ServiceDAO;
import model.User;
import model.Service;
import java.sql.SQLException;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import java.util.*;
import javax.net.ssl.SSLSession;


@WebServlet("/view/checkout")
public class CheckoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CheckoutServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            double price = service.getPrice();
            double serviceCharge = price * 0.10;
            double gst = price * 0.09;
            double totalPrice = price + serviceCharge + gst;
    
            // Pass Stripe publishable key to JSP
            request.setAttribute("publishableKey", "pk_test_51QolZV06Ts4RMwpRSG4uC8QbeC95yG3OBBt8Jtjzp8JmrAAoziu9nqv7cQLKW1MdT0ZptuXND79c647l50JI8hqQ00F1GrdeNg");
            request.setAttribute("service", service);
            request.setAttribute("appointmentId", appointmentId);
            request.setAttribute("totalPrice", totalPrice);
            request.getRequestDispatcher("/view/checkout.jsp").forward(request, response);
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				Stripe.apiKey =  "sk_test_51QolZV06Ts4RMwpR45oPBuPXPU8A9A265lOgy2BmdB8vsQM8PykX46IpvkIm6OXdmXkRo9djJLOvHYeVaogkUJGx00eM2VD7HR";
				
				String serviceName = request.getParameter("serviceName");
				String serviceDetails = request.getParameter("serviceDetails");
				String serviceImage = request.getParameter("serviceImage");
				int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
				int serviceId = Integer.parseInt(request.getParameter("serviceId"));
				double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
				long totalPriceCents = (long) (totalPrice * 100);
		
				// Get the session object
				HttpSession httpSession = request.getSession();
				User user = (User) httpSession.getAttribute("user");
				String email = null;
		
				if (user != null) {
						email = user.getEmail();  // Retrieve the email from the session
				}
		
				try {
						// Create a Stripe session
						SessionCreateParams params = SessionCreateParams.builder()
														.setMode(SessionCreateParams.Mode.PAYMENT)
														// Include appointmentId in the metadata for later reference
														.putMetadata("appointmentId", String.valueOf(appointmentId))
														.setSuccessUrl("http://localhost:8080/JAD_CA1/view/payment?appointmentId=" + appointmentId)
														.setCancelUrl("http://localhost:8080/JAD_CA1/view/serviceHistory")
														.setCustomerEmail(email)  // Set the customer email
														.addLineItem(
																SessionCreateParams.LineItem.builder()
																		.setQuantity(1L)
																		.setPriceData(
																				SessionCreateParams.LineItem.PriceData.builder()
																						.setCurrency("sgd")
																						.setUnitAmount(totalPriceCents)
																						.setProductData(
																								SessionCreateParams.LineItem.PriceData.ProductData.builder()
																										.setName(serviceName)
																										.setDescription(serviceDetails)
																										.addImage(serviceImage)
																										.build()
																						)
																						.build()
																		)
																		.build()
														)
														.build();
		
						// Create a session with Stripe
						Session session = Session.create(params);
						
						// Send JSON response with the session id
						response.setContentType("application/json");
						response.getWriter().write("{\"id\": \"" + session.getId() + "\"}");
		
				} catch (StripeException e) {
						throw new ServletException(e);
				}
		}
	
}
