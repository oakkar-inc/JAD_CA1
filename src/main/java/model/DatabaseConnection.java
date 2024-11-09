package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String URL = "jdbc:postgresql://ep-little-darkness-a1ma8pns.ap-southeast-1.aws.neon.tech/cleaning-service-web";
    private static final String USER = "neondb_owner";
    private static final String PASSWORD = "H8ZRLYyr2vcz";
    
    public static Connection getConnection() throws SQLException {
        try {
        	Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new SQLException("Database connection error", e);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }
}