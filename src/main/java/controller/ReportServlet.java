package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import model.ReportDAO;
import model.CustomerReport;
import model.ServiceReport;

@WebServlet("/view/report")
public class ReportServlet extends HttpServlet {
    private ReportDAO reportDAO;

    public ReportServlet() {
        super();
        reportDAO = new ReportDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Fetching data for top customers and most demanded services
            List<CustomerReport> topCustomers = reportDAO.getTopCustomers();
            List<ServiceReport> mostDemandedServices = reportDAO.getMostDemandedServices();

            // Fetching monthly earnings data
            List<Map<String, Object>> monthlyEarnings = reportDAO.getMonthlyEarnings();

            // Setting attributes for use in the JSP
            request.setAttribute("topCustomers", topCustomers);
            request.setAttribute("mostDemandedServices", mostDemandedServices);
            request.setAttribute("monthlyEarnings", monthlyEarnings);

            // Forwarding the request to the report.jsp
            request.getRequestDispatcher("/view/report.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error fetching reports", e);
        }
    }
}
