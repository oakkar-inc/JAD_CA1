
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ServiceHistoryDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/view/payment")
public class PaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PaymentServlet() {
        super();
    }

     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));

        try {
            // Update status to 'paid' in the database
            ServiceHistoryDAO serviceHistoryDAO = new ServiceHistoryDAO();
            serviceHistoryDAO.updateAppointmentStatus(appointmentId, 3);

            // Redirect to service history page
            response.sendRedirect("/JAD_CA1/view/serviceHistory");

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
