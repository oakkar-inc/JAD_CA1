package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import model.Category;
import model.CategoryDAO;
import model.Service;
import model.ServiceDAO;

/**
 * Servlet implementation class ServiceServlet
 */
@WebServlet("/view/service")
public class ServiceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryDAO categoryDAO;
    private ServiceDAO serviceDAO;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceServlet() {
        super();
        categoryDAO = new CategoryDAO();
        serviceDAO = new ServiceDAO();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String catIdParam = request.getParameter("catId");

        if (catIdParam == null || catIdParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Category ID is required.");
            return;
        }

        try {
            int categoryId = Integer.parseInt(catIdParam);

            // Retrieve category details
            Category category = categoryDAO.getCategoryById(categoryId);

            if (category == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Category not found.");
                return;
            }

            // Retrieve services for the category
            List<Service> serviceList = serviceDAO.getServicesByCategoryId(categoryId);

            // Set category and service details as request attributes
            request.setAttribute("category", category);
            request.setAttribute("serviceList", serviceList);

            // Forward to service.jsp
            request.getRequestDispatcher("/view/service.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Category ID format.");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving data from the database.");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
