package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import model.ServiceHistory;
import model.ServiceHistoryDAO;
import model.User;

/**
 * Servlet implementation class ServiceHistoryServlet
 */
@WebServlet("/view/serviceHistory")
public class ServiceHistoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ServiceHistoryDAO serviceHistoryDAO;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceHistoryServlet() {
        super();
        serviceHistoryDAO = new ServiceHistoryDAO();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the logged-in user from the session
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            response.sendRedirect("/login");
            return;
        }

        try {
            // Fetch the list of service history for the user
            List<ServiceHistory> serviceHistoryList = serviceHistoryDAO.getAllAppointmentsByUserId(user.getId());
            // Pass service history to the JSP
            request.setAttribute("serviceHistoryList", serviceHistoryList);

            // Forward to the service history page
            request.getRequestDispatcher("/view/serviceHistory.jsp").forward(request, response);
        } catch (SQLException e) {
            // Log the error (you can implement a logging mechanism here)
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to fetch service history.");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String appointmentIdStr = request.getParameter("appointmentId");
        String action = request.getParameter("action"); // "cancel", "payment", or "feedback"
    
        if (appointmentIdStr == null || appointmentIdStr.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Appointment ID is required.");
            return;
        }
    
        try {
            int appointmentId = Integer.parseInt(appointmentIdStr);
    
            if ("cancel".equals(action)) {
                serviceHistoryDAO.updateAppointmentStatus(appointmentId, 6);
                response.sendRedirect(request.getContextPath() + "/view/serviceHistory");
    
            } else if ("feedback".equals(action)) { 
                String feedback = request.getParameter("feedback");
                if (feedback == null || feedback.trim().isEmpty()) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Feedback cannot be empty.");
                    return;
                }
                serviceHistoryDAO.addFeedbackByAppointmentId(appointmentId, feedback);
                response.sendRedirect(request.getContextPath() + "/view/serviceHistory");
    
            } else if ("payment".equals(action)) { 
                response.sendRedirect(request.getContextPath() + "/view/payment.jsp?appointmentId=" + appointmentId);
    
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action.");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid appointment ID.");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to process the action.");
        }
    }
    
}
