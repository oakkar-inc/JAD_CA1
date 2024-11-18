package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    /**
     * Fetch all users
     * @return List<User>
     * @throws SQLException
     */
    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM cs_user";  // SQL query to fetch all users
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
            	User user = new User(rs.getInt("user_id"),rs.getString("first_name"),rs.getString("last_name"), rs.getString("mobile"), 
            			rs.getString("email"), rs.getString("password"));
                userList.add(user);
            }
        }
        return userList;
    }
    
    /**
     * Insert a new user into user table
     * @param user
     * @return userId
     * @throws SQLException
     */
    public int insertUser(User user) throws SQLException {
    	int userId = -1;
    	
    	String query = "INSERT INTO cs_user (password, role_id, first_name, last_name, email, mobile) VALUES (?,?,?,?,?,?)";
    	
    	try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
    		    pstmt.setString(1, user.getPassword());
    		    pstmt.setInt(2, 2);
    		    pstmt.setString(3, user.getFirstName());
    		    pstmt.setString(4, user.getLastName());
    		    pstmt.setString(5, user.getEmail());
    		    pstmt.setString(6, user.getMobile());
    		    int affectedRows = pstmt.executeUpdate();
    		    if (affectedRows > 0) {
                    try (ResultSet rs = pstmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            userId = rs.getInt(1); // Retrieve the generated userId
                        }
                    }
                }
    	}
    	
    	return userId;	
    }
    
    /**
     * Insert a new address into address table
     * @param postal
     * @param floor
     * @param unit
     * @return
     * @throws SQLException
     */
    public int insertAddress(String postal, int floor, int unit) throws SQLException {
    	int addressId = -1;
    	
    	String query = "INSERT INTO cs_address (postal, floor, unit) VALUES (?,?,?)";
    	
    	try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
    		    pstmt.setString(1, postal);
    		    pstmt.setInt(2, floor);
    		    pstmt.setInt(3, unit);
    		    
    		    int affectedRows = pstmt.executeUpdate();
    		    if (affectedRows > 0) {
                    try (ResultSet rs = pstmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            addressId = rs.getInt(1);
                        }
                    }
                }
    	}
    	
    	return addressId;
    }
    
    /**
     * Insert a relation between user and address
     * @param userId
     * @param addressId
     * @throws SQLException
     */
    public void insertUserAddressRelation (int userId, int addressId) throws SQLException {
    	String query = "INSERT INTO cs_user_address (user_id, address_id, is_default) VALUES (?,?,?)";
    	
    	try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
       		    pstmt.setInt(1, userId);
       		    pstmt.setInt(2, addressId);
       		    pstmt.setBoolean(3, false);
       		    pstmt.executeUpdate();
       	}
    }
    
    public String getUserPasswordByEmail(String email) throws SQLException{
    	String password = null;
    	
    	String query = "SELECT * FROM cs_user WHERE email = ?";
    	
    	try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
    		pstmt.setString(1, email);
    		ResultSet rs = pstmt.executeQuery();
    		if (rs.next()) {
                password = rs.getString("password");
    		}
    	}
    	return password;
    }
}
