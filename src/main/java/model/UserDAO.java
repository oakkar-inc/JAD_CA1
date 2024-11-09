package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // Method to get all users
    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM cs_user";  // SQL query to fetch all users
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
            	User user = new User(rs.getInt("user_id"),rs.getString("first_name"),rs.getString("last_name"), rs.getString("email"),
                		rs.getString("password"));
                userList.add(user);
            }
        }
        return userList;
    }
}
