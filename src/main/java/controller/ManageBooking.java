package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.ServiceHistory;
import model.ServiceHistoryDAO;
import model.Status;
import model.StatusDAO;
import model.Service;
import model.ServiceDAO;

@WebServlet("/view/manageBooking")
public class ManageBooking extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ServiceHistoryDAO serviceHistoryDAO;
    private StatusDAO statusDAO;
    private ServiceDAO serviceDAO;

    @Override
    public void init() throws ServletException {
        serviceHistoryDAO = new ServiceHistoryDAO();
        statusDAO = new StatusDAO();
        serviceDAO = new ServiceDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve filter parameters
            String nameEmailFilter = request.getParameter("nameEmailFilter");
            String serviceFilter = request.getParameter("serviceFilter");
            String statusFilter = request.getParameter("statusFilter");
            String dateFilterOption = request.getParameter("dateFilterOption");
            String specificDate = request.getParameter("specificDate");
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
    
            // Debugging log
            System.err.println("Name/Email Filter: " + nameEmailFilter);
            System.err.println("Service Filter: " + serviceFilter);
            System.err.println("Status Filter: " + statusFilter);
            System.err.println("Date Filter Option: " + dateFilterOption);
            System.err.println("Specific Date: " + specificDate);
            System.err.println("Start Date: " + startDate);
            System.err.println("End Date: " + endDate);
    
            // Fetch filtered or all service history records
            List<ServiceHistory> serviceHistoryList = serviceHistoryDAO.getFilteredAppointments(
                nameEmailFilter, serviceFilter, statusFilter, dateFilterOption, specificDate, startDate, endDate
            );
    
            request.setAttribute("serviceHistoryList", serviceHistoryList);
    
            // Fetch status and service lists
            List<Status> statusList = statusDAO.getAllStatuses();
            request.setAttribute("statusList", statusList);
    
            List<Service> serviceList = serviceDAO.fetchAllServices();
            request.setAttribute("serviceList", serviceList);
    
            // Forward to JSP
            request.getRequestDispatcher("/view/manageBooking.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error retrieving service history", e);
        }
    }
    
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the appointment ID and status from the form submission
        int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
        int statusId = Integer.parseInt(request.getParameter("status"));
        try {
            // Update the status of the booking in the database
            serviceHistoryDAO.updateAppointmentStatus(appointmentId, statusId);

            // Redirect to the same page to see the updated status
            response.sendRedirect("manageBooking");
        } catch (SQLException e) {
            throw new ServletException("Error updating service status", e);
        }
    }
}