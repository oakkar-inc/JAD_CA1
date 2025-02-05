package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UserDAO;
import model.Address;
import model.AddressDAO;
import model.User;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class UserInfoServlet
 */
@WebServlet("/UserInfo")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDAO userDAO;   
    private AddressDAO addressDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfoServlet() {
        super();
        userDAO = new UserDAO();
        addressDAO = new AddressDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		int roleId = Integer.parseInt(request.getParameter("roleId"));
		Boolean byAdmin = Boolean.parseBoolean(request.getParameter("byAdmin"));
		User user = new User(userId, name, mobile, email, password, roleId);
		try {
			userDAO.updateUserInfo(user);
			List<Address> addressList = addressDAO.getAddressListByUserId(user.getId());
			user.setAddresses(addressList);
			
            if(byAdmin) {
            	response.sendRedirect(request.getContextPath() + "/view/manageUser");
            } else {
            	HttpSession session = request.getSession(false);
                session.setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/view/profile.jsp");
            }
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
