package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Address;
import model.AddressDAO;
import model.UserDAO;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class AddressServlet
 */
@WebServlet("/api/address")
public class AddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO userDAO;
	AddressDAO addressDAO;	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddressServlet() {
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
		String postal = request.getParameter("postal");
		int floor = Integer.parseInt(request.getParameter("floor"));
		int unit = Integer.parseInt(request.getParameter("unit"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		try {
			int addressId = addressDAO.insertAddress(postal, floor, unit);
	        userDAO.insertUserAddressRelation(userId, addressId);
	        
	        List<Address> addressList = addressDAO.getAddressListByUserId(userId);
            HttpSession session = request.getSession();
            session.setAttribute("addressList", addressList);
			response.sendRedirect("/JAD_CA1/view/profile.jsp");
            return;
		} catch (Exception e ) {
			System.out.println(e.getMessage());
            return;
		}
	}

}
