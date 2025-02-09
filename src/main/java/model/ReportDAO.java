package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportDAO {
    // Get Top 10 Customers by Total Booking Value
    public List<CustomerReport> getTopCustomers() throws SQLException {
        List<CustomerReport> customers = new ArrayList<>();
        String query = "SELECT u.user_id, u.email, u.name, SUM(s.price) AS total_spent " +
                       "FROM cs_appointment a " +
                       "JOIN cs_user u ON a.user_id = u.user_id " +
                       "JOIN cs_service s ON a.service_id = s.service_id " +
                       "WHERE a.status_id IN (3, 4, 5) " +  // Include only appointments with status 3, 4, or 5
                       "GROUP BY u.user_id " +
                       "ORDER BY total_spent DESC " +
                       "LIMIT 10";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                customers.add(new CustomerReport(
                    rs.getInt("user_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getDouble("total_spent")
                ));
            }
        }
        return customers;
    }
    

    // Get Most Demanded Cleaning Services
    public List<ServiceReport> getMostDemandedServices() throws SQLException {
        List<ServiceReport> services = new ArrayList<>();
        String query = "SELECT s.service_id, s.name, COUNT(a.service_id) AS booking_count " +
                       "FROM cs_appointment a " +
                       "JOIN cs_service s ON a.service_id = s.service_id " +
                       "GROUP BY s.service_id " +
                       "ORDER BY booking_count DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                services.add(new ServiceReport(
                    rs.getInt("service_id"),
                    rs.getString("name"),
                    rs.getInt("booking_count")
                ));
            }
        }
        return services;
    }

    public List<Map<String, Object>> getMonthlyEarnings() throws SQLException {
    List<Map<String, Object>> monthlyEarnings = new ArrayList<>();
    String query = "SELECT EXTRACT(MONTH FROM a.created_at) AS month, " +
                   "EXTRACT(YEAR FROM a.created_at) AS year, " +
                   "SUM(s.price) AS total_earned " +
                   "FROM cs_appointment a " +
                   "JOIN cs_service s ON a.service_id = s.service_id " +
                   "WHERE a.status_id IN (3, 4, 5) " +  // Paid status
                   "GROUP BY EXTRACT(YEAR FROM a.created_at), EXTRACT(MONTH FROM a.created_at) " +
                   "ORDER BY year, month";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query);
         ResultSet rs = pstmt.executeQuery()) {

        while (rs.next()) {
            Map<String, Object> monthlyData = new HashMap<>();
            monthlyData.put("month", rs.getInt("month"));
            monthlyData.put("year", rs.getInt("year"));
            monthlyData.put("total_earned", rs.getDouble("total_earned"));
            monthlyEarnings.add(monthlyData);
        }
    }
    return monthlyEarnings;
}
}
