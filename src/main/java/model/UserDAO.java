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
        String query = "SELECT * FROM cs_user ORDER BY user_id";  // SQL query to fetch all users
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
            	User user = new User(rs.getInt("user_id"),rs.getString("name"), rs.getString("mobile"), 
            			rs.getString("email"), rs.getString("password"), rs.getInt("role_id"));
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
    	
    	String query = "INSERT INTO cs_user (password, role_id, name, email, mobile) VALUES (?,?,?,?,?)";
    	
    	try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
    		    pstmt.setString(1, user.getPassword());
    		    pstmt.setInt(2, user.getRoleId());
    		    pstmt.setString(3, user.getName());
    		    pstmt.setString(4, user.getEmail());
    		    pstmt.setString(5, user.getMobile());
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
    
    /**
     * Get the user information using email
     * @param email
     * @return User
     * @throws SQLException
     */
    public User getUserByEmail(String email) throws SQLException{
    	User user = null;
    	
    	String query = "SELECT * FROM cs_user WHERE email = ?";
    	
    	try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
    		pstmt.setString(1, email);
    		ResultSet rs = pstmt.executeQuery();
    		if (rs.next()) {
               user = new User(rs.getInt("user_id"),
            		   rs.getString("name"),
            		   rs.getString("mobile"),
                       rs.getString("email"),
                       rs.getString("password"),
                       rs.getInt("role_id"));
    		}
    	}
    	return user;
    }
    
    public void updateUserInfo(User user) throws SQLException {
    	String query = "UPDATE cs_user SET name = ?, mobile = ?, email = ?, password = ?, role_id = ? WHERE user_id = ?";
    	
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
        	pstmt.setString(1, user.getName());
        	pstmt.setString(2, user.getMobile());
        	pstmt.setString(3, user.getEmail());
        	pstmt.setString(4, user.getPassword());
        	pstmt.setInt(5, user.getRoleId());
        	pstmt.setInt(6, user.getId());
        	
        	pstmt.executeUpdate();
        }
    }
    
    
    public void deleteUser(int userId) throws SQLException {
    	String query = "DELETE FROM cs_user WHERE user_id =?";
    	try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
    		    pstmt.setInt(1, userId);
    		    
                pstmt.executeUpdate();
    	}
    }
    
}
