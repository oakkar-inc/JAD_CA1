package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDAO {
	/**
     * Get the list of user addresses for the given user id
     * @param userId
     * @return
     * @throws SQLException
     */
    public List<Address> getAddressListByUserId(int userId) throws SQLException {
    	List<Address> addressList = new ArrayList<>();
        String query = "SELECT * FROM cs_address WHERE address_id IN (SELECT address_id FROM cs_user_address WHERE user_id =?) ORDER BY address_id";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, userId);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    Address address = new Address(rs.getInt("address_id"), rs.getString("postal"), rs.getInt("floor"), rs.getInt("unit"));
                    addressList.add(address);
                }
        }
        return addressList;
    }
    
    /**
     * Insert a new address into address table
     * @param postal
     * @param floor
     * @param unit
     * @return
     * @throws SQLException
     */
    public int insertAddress(String postal, int floor, int unit) throws SQLException {
    	int addressId = -1;
    	
    	String query = "INSERT INTO cs_address (postal, floor, unit) VALUES (?,?,?)";
    	
    	try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
    		    pstmt.setString(1, postal);
    		    pstmt.setInt(2, floor);
    		    pstmt.setInt(3, unit);
    		    
    		    int affectedRows = pstmt.executeUpdate();
    		    if (affectedRows > 0) {
                    try (ResultSet rs = pstmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            addressId = rs.getInt(1);
                        }
                    }
                }
    	}
    	
    	return addressId;
    }
    
    public void updateAddress(Address address) throws SQLException {
    	String query = "UPDATE cs_address SET postal=?, floor=?, unit=? WHERE address_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, address.getPostal());
                pstmt.setInt(2, address.getFloor());
                pstmt.setInt(3, address.getUnit());
                pstmt.setInt(4, address.getId());
                
                pstmt.executeUpdate();
        }
    }
    
    public void deleteAddress(int addressId) throws SQLException {
    	
    	String query = "DELETE FROM cs_address WHERE address_id =?";
    	try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
    		    pstmt.setInt(1, addressId);
    		    
                pstmt.executeUpdate();
    	}
    }
}
