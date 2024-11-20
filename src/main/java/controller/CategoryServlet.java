package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import model.Category;
import model.CategoryDAO;

/**
 * Servlet implementation class CAtegoryServlet
 */
@WebServlet("/view/category")
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

        response.getWriter().append("Served at: ").append(request.getContextPath());
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
