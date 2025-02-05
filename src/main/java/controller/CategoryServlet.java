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

/**
 * Servlet implementation class CAtegoryServlet
 */
@WebServlet("/view/category/*")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    CategoryDAO categoryDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
		categoryDAO = new CategoryDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Category> categoryList = categoryDAO.getAllCategories();
            request.setAttribute("categoryList", categoryList);
            
            System.out.print(categoryList.size());
            request.getRequestDispatcher("/view/category.jsp").forward(request, response);
        } catch (SQLException e) {
            // Log the exception and send an error message to the user
            e.printStackTrace();  // Or use a logger to log the error
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving categories.");
        }
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String image_url = request.getParameter("image_url");
		
		try {
			categoryDAO.insertCategory(name, description, image_url);
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
    	
    	int categoryId = Integer.parseInt(pathInfo.substring(1));
    	
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
        String description = jsonObject.get("description").getAsString();
		String imageUrl = jsonObject.get("imgUrl").getAsString();
		
		try {
			categoryDAO.updateCategory(name, description, imageUrl, categoryId);
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
    	
    	int categoryId = Integer.parseInt(pathInfo.substring(1));
    	
    	try {
    		categoryDAO.deleteCategory(categoryId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
            return;
		}
    	
    }

}
