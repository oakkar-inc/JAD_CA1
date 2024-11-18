package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.UserDAO;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/api/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
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

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		try {
			String correctPassword = userDAO.getUserPasswordByEmail(email);
			if(password.equals(correctPassword)) {
				response.sendRedirect("/JAD_CA1/view/home.jsp");
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
