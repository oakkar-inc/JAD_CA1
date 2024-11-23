package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    /**
     * Fetch all appointments
     * @return List<Appointment>
     * @throws SQLException
     */
    public List<Appointment> getAllAppointments() throws SQLException {
        List<Appointment> appointmentList = new ArrayList<>();
        String query = "SELECT * FROM cs_appointment";  // SQL query to fetch all appointments
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Appointment appointment = new Appointment(
                        rs.getInt("appointment_id"),
                        rs.getInt("user_id"),
                        rs.getInt("service_id"),  // Added service_id
                        rs.getInt("category_id"), // Added category_id
                        rs.getString("booking_name"),
                        rs.getInt("status_id"),
                        rs.getString("booking_phone"),
                        rs.getInt("address_id"),
                        rs.getString("special_request"),
                        rs.getDate("date"),
                        rs.getDouble("rating"),
                        rs.getString("feedback")
                );
                appointmentList.add(appointment);
            }
        }
        return appointmentList;
    }

    /**
     * Fetch appointment by appointment id
     * @param appointmentId
     * @return Appointment
     * @throws SQLException
     */
    public Appointment getAppointmentById(int appointmentId) throws SQLException {
        Appointment appointment = null;
        String query = "SELECT * FROM cs_appointment WHERE appointment_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, appointmentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    appointment = new Appointment(
                            rs.getInt("appointment_id"),
                            rs.getInt("user_id"),
                            rs.getInt("service_id"),  // Added service_id
                            rs.getInt("category_id"), // Added category_id
                            rs.getString("booking_name"),
                            rs.getInt("status_id"),
                            rs.getString("booking_phone"),
                            rs.getInt("address_id"),
                            rs.getString("special_request"),
                            rs.getDate("date"),
                            rs.getDouble("rating"),
                            rs.getString("feedback")
                    );
                }
            }
        }
        return appointment;
    }

    /**
     * Insert a new appointment into the cs_appointment table
     * @param userId
     * @param statusId
     * @param bookingName
     * @param bookingPhone
     * @param addressId
     * @param appointmentDate
     * @param specialRequest
     * @param rating
     * @param feedback
     * @param serviceId
     * @param categoryId
     * @return appointmentId
     * @throws SQLException
     */
    public int insertAppointment(int userId, int statusId, String bookingName, String bookingPhone, int addressId,
                             Date appointmentDate, String specialRequest, Double rating, String feedback, 
                             int serviceId, int categoryId) throws SQLException {
        int appointmentId = -1;
        String query = "INSERT INTO cs_appointment (user_id, status_id, booking_name, booking_phone, address_id, date, " +
                       "special_request, rating, feedback, service_id, category_id) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, userId);
            pstmt.setInt(2, statusId);
            pstmt.setString(3, bookingName);
            pstmt.setString(4, bookingPhone);
            pstmt.setInt(5, addressId);
            pstmt.setDate(6, appointmentDate);
            pstmt.setString(7, specialRequest);

            if (rating == null) {
                pstmt.setNull(8, Types.DOUBLE); 
            } else {
                pstmt.setDouble(8, rating); 
            }

            if (feedback == null) {
                pstmt.setNull(9, Types.VARCHAR); 
            } else {
                pstmt.setString(9, feedback);  
            }

            pstmt.setInt(10, serviceId);  // Set service_id
            pstmt.setInt(11, categoryId); // Set category_id

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        appointmentId = rs.getInt(1);
                    }
                }
            }
        }
        return appointmentId;
    }

    /**
     * Update appointment by appointment id
     * @param userId
     * @param statusId
     * @param bookingName
     * @param bookingPhone
     * @param addressId
     * @param appointmentDate
     * @param specialRequest
     * @param rating
     * @param feedback
     * @param serviceId
     * @param categoryId
     * @param appointmentId
     * @return number of affected rows
     * @throws SQLException
     */
    public int updateAppointment(int userId, int statusId, String bookingName, String bookingPhone, int addressId,
                                 Date appointmentDate, String specialRequest, Double rating, String feedback, 
                                 int serviceId, int categoryId, int appointmentId) throws SQLException {
        String query = "UPDATE cs_appointment SET user_id = ?, status_id = ?, booking_name = ?, booking_phone = ?, address_id = ?, " +
                       "date = ?, special_request = ?, rating = ?, feedback = ?, service_id = ?, category_id = ? " +
                       "WHERE appointment_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, statusId);  // Added status_id
            pstmt.setString(3, bookingName);
            pstmt.setString(4, bookingPhone);
            pstmt.setInt(5, addressId);
            pstmt.setDate(6, appointmentDate);
            pstmt.setString(7, specialRequest);
            pstmt.setDouble(8, rating);
            pstmt.setString(9, feedback);
            pstmt.setInt(10, serviceId); // Set service_id
            pstmt.setInt(11, categoryId); // Set category_id
            pstmt.setInt(12, appointmentId);

            return pstmt.executeUpdate();
        }
    }

    /**
     * Delete appointment by appointment id
     * @param appointmentId
     * @return number of affected rows
     * @throws SQLException
     */
    public int deleteAppointment(int appointmentId) throws SQLException {
        String query = "DELETE FROM cs_appointment WHERE appointment_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, appointmentId);
            return pstmt.executeUpdate();
        }
    }

    @Override
    public String toString() {
        return "AppointmentDAO []";
    }
}
