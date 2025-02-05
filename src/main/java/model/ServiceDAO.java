package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAO {

    /**
     * Fetch all services
     * @return List<Service>
     * @throws SQLException
     */
    public List<Service> fetchAllServices() throws SQLException {
        List<Service> serviceList = new ArrayList<>();
        String query = "SELECT * FROM cs_service";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Service service = new Service(
                    rs.getInt("service_id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getString("details"),
                    rs.getString("image_url"),
                    rs.getInt("category_id")
                );
                serviceList.add(service);
            }
        }
        return serviceList;
    }

    /**
     * Fetch services by category ID
     * @param categoryId
     * @return List<Service>
     * @throws SQLException
     */
    public List<Service> getServicesByCategoryId(int categoryId) throws SQLException {
        List<Service> serviceList = new ArrayList<>();
        String query = "SELECT * FROM cs_service WHERE category_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, categoryId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Service service = new Service(
                        rs.getInt("service_id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("details"),
                        rs.getString("image_url"),
                        rs.getInt("category_id")
                    );
                    serviceList.add(service);
                }
            }
        }
        return serviceList;
    }

    /**
     * Fetch a service by service ID
     * @param serviceId the ID of the service to fetch
     * @return Service object or null if not found
     * @throws SQLException
     */
    public Service getServiceByServiceId(int serviceId) throws SQLException {
        String query = "SELECT * FROM cs_service WHERE service_id = ?";
        Service service = null;

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, serviceId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    service = new Service(
                        rs.getInt("service_id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("details"),
                        rs.getString("image_url"),
                        rs.getInt("category_id")
                    );
                }
            }
        }
        return service;
    }


    /**
     * Insert a new service
     * @param name
     * @param price
     * @param details
     * @param imageUrl
     * @param categoryId
     * @return generated service ID
     * @throws SQLException
     */
    public int insertService(String name, double price, String details, String imageUrl, int categoryId) throws SQLException {
        int serviceId = -1;
        String query = "INSERT INTO cs_service (name, price, details, image_url, category_id) VALUES (?,?,?,?,?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setString(3, details);
            pstmt.setString(4, imageUrl);
            pstmt.setInt(5, categoryId);
            
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        serviceId = rs.getInt(1);
                    }
                }
            }
        }
        return serviceId;
    }

    /**
     * Update service by service ID
     * @param name
     * @param price
     * @param details
     * @param imageUrl
     * @param categoryId
     * @param serviceId
     * @return number of affected rows
     * @throws SQLException
     */
    public int updateServiceByServiceId(String name, double price, String details, String imageUrl, int categoryId, int serviceId) throws SQLException {
        int affectedRows = -1;
        String query = "UPDATE cs_service SET name = ?, price = ?, details = ?, image_url = ?, category_id = ? WHERE service_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setString(3, details);
            pstmt.setString(4, imageUrl);
            pstmt.setInt(5, categoryId);
            pstmt.setInt(6, serviceId);
            
            affectedRows = pstmt.executeUpdate();
        }
        return affectedRows;
    }

    /**
     * Delete service by service ID
     * @param serviceId
     * @return number of affected rows
     * @throws SQLException
     */
    public int deleteServiceByServiceId(int serviceId) throws SQLException {
        String query = "DELETE FROM cs_service WHERE service_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, serviceId);
            return pstmt.executeUpdate();
        }
    }

    @Override
    public String toString() {
        return "ServiceDAO []";
    }
}