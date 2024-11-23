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
                        rs.getInt("user_address_id"),
                        rs.getInt("service_id"),
                        rs.getInt("status_id"),
                        rs.getDate("date"),
                        rs.getString("special_request"),
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
                            rs.getInt("user_address_id"),
                            rs.getInt("service_id"),
                            rs.getInt("status_id"),
                            rs.getDate("date"),
                            rs.getString("special_request"),
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
     * @param userAddressId
     * @param serviceId
     * @param statusId
     * @param appointmentDate
     * @param specialRequest
     * @param rating
     * @param feedback
     * @return appointmentId
     * @throws SQLException
     */
    public int insertAppointment(int userAddressId, int serviceId, int statusId, Date appointmentDate,
                                 String specialRequest, Double rating, String feedback) throws SQLException {
        int appointmentId = -1;
        String query = "INSERT INTO cs_appointment (user_address_id, service_id, status_id, date, special_request, rating, feedback) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, userAddressId);
            pstmt.setInt(2, serviceId);
            pstmt.setInt(3, statusId);
            pstmt.setDate(4, appointmentDate);
            pstmt.setString(5, specialRequest);
            pstmt.setDouble(6, rating);
            pstmt.setString(7, feedback);

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
     * @param userAddressId
     * @param serviceId
     * @param statusId
     * @param appointmentDate
     * @param specialRequest
     * @param rating
     * @param feedback
     * @param appointmentId
     * @return
     * @throws SQLException
     */
    public int updateAppointment(int userAddressId, int serviceId, int statusId, Date appointmentDate,
                                 String specialRequest, Double rating, String feedback, int appointmentId) throws SQLException {
        String query = "UPDATE cs_appointment SET user_address_id = ?, service_id = ?, status_id = ?, date = ?, special_request = ?, rating = ?, feedback = ? WHERE appointment_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userAddressId);
            pstmt.setInt(2, serviceId);
            pstmt.setInt(3, statusId);
            pstmt.setDate(4, appointmentDate);
            pstmt.setString(5, specialRequest);
            pstmt.setDouble(6, rating);
            pstmt.setString(7, feedback);
            pstmt.setInt(8, appointmentId);

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
