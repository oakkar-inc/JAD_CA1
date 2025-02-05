package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.*;

import model.User;
import model.UserDAO;
import model.Address;
import model.AddressDAO;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/api/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private AddressDAO addressDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		try {
			User user = userDAO.getUserByEmail(email);
			if(password.equals(user.getPassword()) && user.getRoleId() != 3) {
				
				List<Address> addressList = addressDAO.getAddressListByUserId(user.getId());
				user.setAddresses(addressList);
				HttpSession session = request.getSession();
	            session.setAttribute("user", user);				
	            response.sendRedirect("/JAD_CA1/view/home.jsp");
			} else if (user.getRoleId() == 3) {
				response.sendRedirect(request.getContextPath() + "/view/login.jsp?errMsg=cleaner");
			} else {
				response.sendRedirect("/JAD_CA1/view/login.jsp?errMsg=" + "wrongpassword");
                return;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.sendRedirect("/JAD_CA1/view/login.jsp?errMsg=" + "error");
            return;
		}
		
		
	}

}
