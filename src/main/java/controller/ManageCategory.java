package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import model.Category;
import model.CategoryDAO;
import model.Service;
import model.ServiceDAO;
/**
 * Servlet implementation class ManageCategory
 */
@WebServlet("/view/manageCategory/*")
public class ManageCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO categoryDAO;
	private ServiceDAO serviceDAO;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageCategory() {
        super();
        categoryDAO = new CategoryDAO();
        serviceDAO = new ServiceDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pathInfo = request.getPathInfo();
		
		
		
		try {
			
			List<Category> categoryList = categoryDAO.getAllCategories();
			request.setAttribute("categoryList", categoryList);
			
			if (pathInfo == null) {
				request.getRequestDispatcher("/view/manageCategory.jsp").forward(request, response);
			} else {
				String categoryIdStr = pathInfo.substring(1);
                int categoryId = Integer.parseInt(categoryIdStr);
                Category category = categoryDAO.getCategoryById(categoryId);
                List<Service> serviceList = serviceDAO.getServicesByCategoryId(categoryId);
                request.setAttribute("category", category);
                request.setAttribute("serviceList", serviceList);
                request.getRequestDispatcher("/view/editCategory.jsp").forward(request, response);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
