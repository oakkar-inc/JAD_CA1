package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Address;
import model.AddressDAO;
import model.DatabaseConnection;
import model.User;
import model.UserDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/api/user/*")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO userDAO;
	AddressDAO addressDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
		userDAO = new UserDAO();
		addressDAO = new AddressDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String mobile = request.getParameter("mobile");
		String postal = request.getParameter("postal");
		int floor = Integer.parseInt(request.getParameter("floor"));
		int unit = Integer.parseInt(request.getParameter("unit"));

		User user = new User(-1, name, mobile, email, password, 2);
		try {
			int userId = userDAO.insertUser(user);
			if (userId > 0) {
				int addressId = addressDAO.insertAddress(postal, floor, unit);
				userDAO.insertUserAddressRelation(userId, addressId);

				response.sendRedirect("/JAD_CA1/view/manageUser");
				return;
			}
			response.sendRedirect("/JAD_CA1/view/manageUser");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.sendRedirect("/JAD_CA1/view/addUser.jsp?errMsg=" + "error");
			return;
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		System.out.println(pathInfo);
		int userId = Integer.parseInt(pathInfo.substring(1));
		try {
			userDAO.deleteUser(userId);

			response.sendRedirect("/JAD_CA1/view/manageUser");
			return;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
	}

}
