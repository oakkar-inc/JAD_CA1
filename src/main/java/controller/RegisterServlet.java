package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import model.User;
import model.UserDAO;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/api/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserDAO userDAO;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
        userDAO = new UserDAO();
        
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
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String mobile = request.getParameter("mobile");
		String postal = request.getParameter("postal");
		int floor = Integer.parseInt(request.getParameter("floor"));
		int unit = Integer.parseInt(request.getParameter("unit"));
		
		User user = new User(-1, firstName, lastName, mobile, email, password, 2);
		try {
			int userId = userDAO.insertUser(user);
			if(userId > 0) {
				int addressId = userDAO.insertAddress(postal, floor, unit);
                userDAO.insertUserAddressRelation(userId, addressId);
                HttpSession session = request.getSession();
	            session.setAttribute("user", user);
                response.sendRedirect("/JAD_CA1/view/home.jsp");
                return;
			}
			response.sendRedirect("/JAD_CA1/view/home.jsp");
		} catch (Exception e ) {
			System.out.println(e.getMessage());
			response.sendRedirect("/JAD_CA1/view/register.jsp?errMsg=" + "error");
            return;
		}
		
	}

}
