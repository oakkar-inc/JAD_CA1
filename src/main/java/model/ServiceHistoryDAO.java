package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceHistoryDAO {

    /**
     * Fetch all service history records for a specific user
     * 
     * @param userId
     * @return List<ServiceHistory>
     * @throws SQLException
     */
    public List<ServiceHistory> getAllAppointmentsByUserId(int userId) throws SQLException {
        List<ServiceHistory> serviceHistoryList = new ArrayList<>();
        String query = """
            SELECT a.appointment_id, a.service_date, a.booking_name, a.service_id, s.name, s.price, 
                   ad.floor, ad.unit, ad.postal AS postal, a.special_request AS note, 
                   st.status_name AS status, a.feedback, a.created_at AS booking_date
            FROM cs_appointment a
            JOIN cs_service s ON a.service_id = s.service_id
            JOIN cs_address ad ON a.address_id = ad.address_id
            JOIN cs_status st ON a.status_id = st.status_id
            WHERE a.user_id = ?
            ORDER BY a.service_date DESC
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // Format the address as "#floor-unit Spostal"
                    String formattedAddress = "#" + rs.getInt("floor") + "-" + rs.getInt("unit") + " " + rs.getString("postal");
                    
                    ServiceHistory serviceHistory = new ServiceHistory(
                        rs.getInt("appointment_id"),
                        rs.getDate("service_date"),
                        rs.getString("booking_name"),
                        rs.getInt("service_id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        formattedAddress,  // formatted address
                        rs.getString("note"),
                        rs.getString("status"),
                        rs.getString("feedback"),
                        rs.getDate("booking_date")
                    );
                    serviceHistoryList.add(serviceHistory);
                }
            }
        }
        return serviceHistoryList;
    }

    /**
     * Add or update feedback for a specific appointment
     * 
     * @param appointmentId
     * @param feedback
     * @throws SQLException
     */
    public void addFeedbackByAppointmentId(int appointmentId, String feedback) throws SQLException {
        String query = "UPDATE cs_appointment SET feedback = ? WHERE appointment_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, feedback);
            pstmt.setInt(2, appointmentId);
            pstmt.executeUpdate();
        }
    }

    /**
     * Update the status of an appointment
     * 
     * @param appointmentId
     * @param statusId
     * @throws SQLException
     */
    public void updateAppointmentStatus(int appointmentId, int statusId) throws SQLException {
        String query = "UPDATE cs_appointment SET status_id = ? WHERE appointment_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, statusId);
            pstmt.setInt(2, appointmentId);
            pstmt.executeUpdate();
        }
    }
}
