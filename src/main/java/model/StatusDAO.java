package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatusDAO {
    
    /**
     * Fetch all statuses from the cs_status table
     * @return List<Status>
     * @throws SQLException
     */
    public List<Status> getAllStatuses() throws SQLException {
        List<Status> statusList = new ArrayList<>();
        String query = "SELECT * FROM cs_status";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Status status = new Status(rs.getInt("status_id"), rs.getString("status_name"));
                statusList.add(status);
            }
        }
        return statusList;
    }
    
    /**
     * Fetch a status by ID
     * @param statusId
     * @return Status
     * @throws SQLException
     */
    public Status getStatusById(int statusId) throws SQLException {
        Status status = null;
        String query = "SELECT * FROM cs_status WHERE status_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, statusId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    status = new Status(rs.getInt("status_id"), rs.getString("status_name"));
                }
            }
        }
        return status;
    }
    
    /**
     * Insert a new status
     * @param statusName
     * @return generated status ID
     * @throws SQLException
     */
    public int insertStatus(String statusName) throws SQLException {
        int statusId = -1;
        String query = "INSERT INTO cs_status (status_name) VALUES (?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, statusName);
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        statusId = rs.getInt(1);
                    }
                }
            }
        }
        return statusId;
    }
    
    /**
     * Update a status by ID
     * @param statusId
     * @param statusName
     * @return number of affected rows
     * @throws SQLException
     */
    public int updateStatus(int statusId, String statusName) throws SQLException {
        String query = "UPDATE cs_status SET status_name = ? WHERE status_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, statusName);
            pstmt.setInt(2, statusId);
            return pstmt.executeUpdate();
        }
    }
    
    /**
     * Delete a status by ID
     * @param statusId
     * @return number of affected rows
     * @throws SQLException
     */
    public int deleteStatus(int statusId) throws SQLException {
        String query = "DELETE FROM cs_status WHERE status_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, statusId);
            return pstmt.executeUpdate();
        }
    }
}
