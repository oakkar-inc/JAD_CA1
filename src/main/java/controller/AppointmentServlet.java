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
import model.Service;
import model.Category;
import model.ServiceDAO;
import model.CategoryDAO;
import model.Address;
import model.AddressDAO;
import model.User;

/**
 * Servlet implementation class AppointmentServlet
 */
@WebServlet("/view/appointment")
public class AppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServiceDAO serviceDAO;
    private CategoryDAO categoryDAO;
    private AddressDAO addressDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppointmentServlet() {
        super();
        // TODO Auto-generated constructor stub
		serviceDAO = new ServiceDAO();
        categoryDAO = new CategoryDAO();
        addressDAO = new AddressDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String serIdParam = request.getParameter("serId");
        String catIdParam = request.getParameter("catId");

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }else {
            int userId = user.getId();
            List<Address> addressList = null;
			try {
				addressList = addressDAO.getAddressListByUserId(userId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            request.setAttribute("addressList", addressList);
        }

        if (serIdParam == null || catIdParam == null || serIdParam.isEmpty() || catIdParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Service ID and Category Id are required.");
            return;
        }

        try {
            int serviceId = Integer.parseInt(serIdParam);
            int categoryId = Integer.parseInt(catIdParam);

            // Retrieve service details
			Service service = serviceDAO.getServiceByServiceId(serviceId);
            Category category = categoryDAO.getCategoryById(categoryId);

            if (service == null || category == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Service or Category not found.");
                return;
            }

            request.setAttribute("service", service);
            request.setAttribute("category", category);

            // Forward to service.jsp
            request.getRequestDispatcher("/view/appointment.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Service ID format.");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving data from the database.");
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
