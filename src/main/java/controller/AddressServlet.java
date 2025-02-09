package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Address;
import model.AddressDAO;
import model.User;
import model.UserDAO;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class AddressServlet
 */
@WebServlet("/api/address/*")
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
		Boolean byAdmin = Boolean.parseBoolean(request.getParameter("byAdmin"));
		try {
			int addressId = addressDAO.insertAddress(postal, floor, unit);
	        userDAO.insertUserAddressRelation(userId, addressId);
	        
	        if (byAdmin) {
	        	response.sendRedirect(request.getContextPath() + "/ManageUser");
	        	return;
	        }
	        
	        List<Address> addressList = addressDAO.getAddressListByUserId(userId);
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("user");
            user.setAddresses(addressList);
			response.sendRedirect(request.getContextPath() + "/view/profile.jsp");
            return;
		} catch (Exception e ) {
			System.out.println(e.getMessage());
            return;
		}
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Read the JSON body from the request
        StringBuilder jsonBuilder = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
        }

        // Parse the JSON string
        String jsonString = jsonBuilder.toString();
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);

        // Access JSON properties
        int addressId = jsonObject.get("addressId").getAsInt();
        String postal = jsonObject.get("postal").getAsString();
        int floor = jsonObject.get("floor").getAsInt();
		int unit = jsonObject.get("unit").getAsInt();
		Boolean byAdmin = jsonObject.get("byAdmin").getAsBoolean() || false;
		
		try {
			Address address = new Address(addressId, postal, floor, unit);
            addressDAO.updateAddress(address);
            
            if(byAdmin) {
            	response.sendRedirect(request.getContextPath() + "/ManageUser");
                return;
            }
            
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("user");
            
          	List<Address> addressList = addressDAO.getAddressListByUserId(user.getId());
          	user.setAddresses(addressList);
            session.setAttribute("user", user);
            
            response.sendRedirect(request.getContextPath() + "/view/profile.jsp");
            return;
		} catch (Exception e){
			System.out.println(e.getMessage());
            return;
		}
		
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pathInfo = request.getPathInfo();
		int addressId = Integer.parseInt(pathInfo.substring(1));
		
		Boolean byAdmin = false;
        
        try {
            addressDAO.deleteAddress(addressId);
            
            if(byAdmin) {
            	response.sendRedirect(request.getContextPath() + "/ManageUser");
                return;
            }
            
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("user");
            List<Address> addressList = addressDAO.getAddressListByUserId(user.getId());
            user.setAddresses(addressList);
            session.setAttribute("user", user);
    
            response.sendRedirect(request.getContextPath() + "/view/profile.jsp");
            return;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
    }

}
