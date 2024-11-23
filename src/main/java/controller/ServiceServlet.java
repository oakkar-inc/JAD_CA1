package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import model.Category;
import model.CategoryDAO;
import model.Service;
import model.ServiceDAO;

/**
 * Servlet implementation class ServiceServlet
 */
@WebServlet("/view/service/*")
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
    	String name = request.getParameter("name");
    	double price = Double.parseDouble(request.getParameter("price"));
		String details = request.getParameter("details");
		String image_url = request.getParameter("image_url");
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		
		try {
			serviceDAO.insertService(name, price, details, image_url, categoryId);
			response.sendRedirect("/JAD_CA1/view/manageCategory");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
    }
    
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String pathInfo = request.getPathInfo();
    	
    	if(pathInfo == null) {
    		response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Service ID is required.");
                return;
    	}
    	
    	int serviceId = Integer.parseInt(pathInfo.substring(1));
    	
    	// Read the JSON body from the request
        StringBuilder jsonBuilder = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
        }

        // Parse the JSON string
        String jsonString = jsonBuilder.toString();
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);

        // Access JSON properties
        String name = jsonObject.get("name").getAsString();
        double price = jsonObject.get("price").getAsDouble();
        String details = jsonObject.get("details").getAsString();
		String imageUrl = jsonObject.get("imgUrl").getAsString();
		int categoryId = jsonObject.get("categoryId").getAsInt();
		
		try {
			serviceDAO.updateServiceByServiceId(name, price, details, imageUrl, categoryId, serviceId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
            return;
		}
    }
    
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String pathInfo = request.getPathInfo();
    	
    	if(pathInfo == null) {
    		response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Service ID is required.");
                return;
    	}
    	
    	int serviceId = Integer.parseInt(pathInfo.substring(1));
    	
    	try {
			serviceDAO.deleteServiceByServiceId(serviceId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
            return;
		}
    	
    }
    
    
}
