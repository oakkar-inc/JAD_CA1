package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceHistoryDAO {

    private static final Logger logger = Logger.getLogger(ServiceHistoryDAO.class.getName());

    /**
     * Fetch all service history records for a specific user
     * 
     * @return List<ServiceHistory>
     * @throws SQLException
     */
    public List<ServiceHistory> getAllAppointments() throws SQLException {
        List<ServiceHistory> serviceHistoryList = new ArrayList<>();
        String query = """
            SELECT a.user_id, u.email, a.appointment_id, a.service_date, a.booking_name, a.service_id, s.name, s.price, 
                   ad.floor, ad.unit, ad.postal AS postal, a.special_request AS note, 
                   a.status_id, st.status_name AS status, a.feedback, a.created_at AS booking_date
            FROM cs_appointment a
            JOIN cs_service s ON a.service_id = s.service_id
            JOIN cs_address ad ON a.address_id = ad.address_id
            JOIN cs_status st ON a.status_id = st.status_id
            JOIN cs_user u ON a.user_id = u.user_id
            ORDER BY a.created_at DESC
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // Format the address as "#floor-unit Spostal"
                    String formattedAddress = "#" + rs.getInt("floor") + "-" + rs.getInt("unit") + " " + rs.getString("postal");
                    
                    ServiceHistory serviceHistory = new ServiceHistory(
                        rs.getInt("user_id"),
                        rs.getString("email"),
                        rs.getInt("appointment_id"),
                        rs.getDate("booking_date"),
                        rs.getString("booking_name"),
                        rs.getInt("service_id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        formattedAddress,  // formatted address
                        rs.getString("note"),
                        rs.getInt("status_id"),
                        rs.getString("status"),
                        rs.getString("feedback"),
                        rs.getDate("service_date"),
                        rs.getInt("helper_id")
                    );
                    serviceHistoryList.add(serviceHistory);
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error executing query: " + query, e);
                throw new SQLException("Error executing the query for fetching appointments.", e);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error connecting to the database", e);
            throw new SQLException("Error establishing a database connection or preparing the statement.", e);
        }
        return serviceHistoryList;
    }


    /**
     * Fetch all service history records for a specific user
     * 
     * @param filterType
     * @param filterValue
     * @return List<ServiceHistory>
     * @throws SQLException
     */
    public List<ServiceHistory> getFilteredAppointments(
        String nameEmail, String service, String status, String dateOption, String specificDate, String startDate, String endDate
    ) throws SQLException {
        
        List<ServiceHistory> serviceHistoryList = new ArrayList<>();

        // Base query
        String query = "SELECT a.user_id, u.email, a.appointment_id, a.service_date, a.booking_name, a.service_id, s.name, s.price, "
                    + "ad.floor, ad.unit, ad.postal AS postal, a.special_request AS note, "
                    + "a.status_id, st.status_name AS status, a.feedback, a.created_at AS booking_date "
                    + "FROM cs_appointment a "
                    + "JOIN cs_service s ON a.service_id = s.service_id "
                    + "JOIN cs_address ad ON a.address_id = ad.address_id "
                    + "JOIN cs_status st ON a.status_id = st.status_id "
                    + "JOIN cs_user u ON a.user_id = u.user_id WHERE 1=1 "; // Always true condition for appending filters dynamically
        // List to hold query parameters
        List<Object> parameters = new ArrayList<>();

        if (nameEmail != null && !nameEmail.trim().isEmpty()) {
            query += "AND (u.email LIKE ? OR a.booking_name LIKE ?) ";
            parameters.add("%" + nameEmail + "%");
            parameters.add("%" + nameEmail + "%");
        }

        if (service != null && !service.isEmpty()) {
            query += "AND s.name = ? ";
            parameters.add(service);
        }

        if (status != null && !status.isEmpty()) {
            query += "AND st.status_name = ? ";
            parameters.add(status);
        }

        if ("date".equals(dateOption) && specificDate != null && !specificDate.isEmpty()) {
            query += "AND a.service_date = ? ";
            parameters.add(Date.valueOf(specificDate)); 
        } else if ("period".equals(dateOption) && startDate != null && endDate != null 
                && !startDate.isEmpty() && !endDate.isEmpty()) {
            query += "AND a.service_date BETWEEN ? AND ? ";
            parameters.add(Date.valueOf(startDate));
            parameters.add(Date.valueOf(endDate));
        }

        query += "ORDER BY a.created_at DESC";  // Sorting

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            for (int i = 0; i < parameters.size(); i++) {
                pstmt.setObject(i + 1, parameters.get(i));
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String formattedAddress = "#" + rs.getInt("floor") + "-" + rs.getInt("unit") + " " + rs.getString("postal");
                    ServiceHistory serviceHistory = new ServiceHistory(
                        rs.getInt("user_id"),
                        rs.getString("email"),
                        rs.getInt("appointment_id"),
                        rs.getDate("booking_date"),
                        rs.getString("booking_name"),
                        rs.getInt("service_id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        formattedAddress,
                        rs.getString("note"),
                        rs.getInt("status_id"),
                        rs.getString("status"),
                        rs.getString("feedback"),
                        rs.getDate("service_date"),
                        rs.getInt("helper_id")

                    );
                    serviceHistoryList.add(serviceHistory);
                }
            }
        }

        return serviceHistoryList;
    }

    

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
            SELECT a.user_id, u.email, a.appointment_id, a.service_date, a.booking_name, a.service_id, s.name, s.price, 
                   ad.floor, ad.unit, ad.postal AS postal, a.special_request AS note, 
                   a.status_id, st.status_name AS status, a.feedback, a.created_at AS booking_date
            FROM cs_appointment a
            JOIN cs_service s ON a.service_id = s.service_id
            JOIN cs_address ad ON a.address_id = ad.address_id
            JOIN cs_status st ON a.status_id = st.status_id
            JOIN cs_user u ON a.user_id = u.user_id
            WHERE a.user_id = ?
            ORDER BY a.created_at DESC
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // Format the address as "#floor-unit Spostal"
                    String formattedAddress = "#" + rs.getInt("floor") + "-" + rs.getInt("unit") + " " + rs.getString("postal");
                    
                    ServiceHistory serviceHistory = new ServiceHistory(
                        rs.getInt("user_id"),
                        rs.getString("email"),
                        rs.getInt("appointment_id"),
                        rs.getDate("booking_date"),
                        rs.getString("booking_name"),
                        rs.getInt("service_id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        formattedAddress,  // formatted address
                        rs.getString("note"),
                        rs.getInt("status_id"),
                        rs.getString("status"),
                        rs.getString("feedback"),
                        rs.getDate("service_date"),
                        rs.getInt("helper_id")
                    );
                    serviceHistoryList.add(serviceHistory);
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error executing query: " + query, e);
                throw new SQLException("Error executing the query for fetching appointments by user ID.", e);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error connecting to the database", e);
            throw new SQLException("Error establishing a database connection or preparing the statement.", e);
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
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error executing update for feedback on appointment ID: " + appointmentId, e);
            throw new SQLException("Error updating feedback for appointment ID: " + appointmentId, e);
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
        String currentStatusQuery = "SELECT status_id, feedback FROM cs_appointment WHERE appointment_id = ?";
        String updateStatusQuery = "UPDATE cs_appointment SET status_id = ?, helper_id = ?, feedback = ? WHERE appointment_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            // First, get the current status and feedback
            try (PreparedStatement pstmt = conn.prepareStatement(currentStatusQuery)) {
                pstmt.setInt(1, appointmentId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        int currentStatusId = rs.getInt("status_id");
                        String currentFeedback = rs.getString("feedback");
                        int currentHelperId = rs.getInt("helper_id");
    
                        // Determine if any changes are required
                        String newFeedback = currentFeedback; // Default: keep current feedback
                        Integer newHelperId = currentHelperId; // Default: keep current helper_id
    
                        // Check if status is changed to 'pending'
                        if (statusId == 1) { // Assuming 'pending' status has ID 1
                            newHelperId = null; // Remove the helper_id
                        }
                        
                        // Check if status is changed from 'completed' to something else
                        if (currentStatusId == 5 && statusId != 5) { // Assuming 'completed' status has ID 5
                            newFeedback = null; // Remove the feedback
                        }
    
                        // Now update the appointment status along with other changes
                        try (PreparedStatement updatePstmt = conn.prepareStatement(updateStatusQuery)) {
                            updatePstmt.setInt(1, statusId);
                            updatePstmt.setObject(2, newHelperId); // Null for helper_id if pending
                            updatePstmt.setObject(3, newFeedback); // Null for feedback if status changes from completed
                            updatePstmt.setInt(4, appointmentId);
                            updatePstmt.executeUpdate();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error executing update for status on appointment ID: " + appointmentId, e);
            throw new SQLException("Error updating status for appointment ID: " + appointmentId, e);
        }
    }
    
}
