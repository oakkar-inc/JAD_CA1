package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AddressDAO;
import model.UserDAO;
import model.User;
import model.Address;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/ManageUser")
public class ManageUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private AddressDAO addressDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageUser() {
        super();
        userDAO = new UserDAO();
        addressDAO = new AddressDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> userList = new ArrayList<User>();
		
		try {
			userList = userDAO.getAllUsers();
			
			for(User user : userList) {
                List<Address> addressList = addressDAO.getAddressListByUserId(user.getId());
                user.setAddresses(addressList);
            }
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		request.setAttribute("userList", userList);
		request.getRequestDispatcher("/view/manageUser.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
