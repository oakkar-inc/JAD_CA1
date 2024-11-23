package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class CategoryDAO {

    /**
     * Fetch all categories
     * @return List<Category>
     * @throws SQLException
     */
    public List<Category> getAllCategories() throws SQLException {
        List<Category> categoryList = new ArrayList<>();
        String query = "SELECT * FROM cs_category";  // SQL query to fetch all categories
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Category category = new Category(rs.getInt("category_id"),rs.getString("name"),rs.getString("description"), rs.getString("image_url"));
                categoryList.add(category);
            }
        }
        return categoryList;
    }

    /**
     * Fetch category by category id
     * @param categoryId
     * @return Category
     * @throws SQLException
     */
    public Category getCategoryById(int categoryId) throws SQLException {
        Category category = null;
        String query = "SELECT * FROM cs_category WHERE category_id = ?"; 
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, categoryId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    category = new Category(rs.getInt("category_id"),rs.getString("name"),rs.getString("description"), rs.getString("image_url"));
                }
            }
        }
        return category;
    }
    
    /**
     * Insert a new category into category table
     * @param name
     * @param description
     * @param image_url
     * @return
     * @throws SQLException
     */
    public int insertCategory(String name, String description, String image_url) throws SQLException {
    	int categoryId = -1;
    	
    	String query = "INSERT INTO cs_category (name, description, image_url) VALUES (?,?,?)";
    	
    	try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
    		    pstmt.setString(1, name);
    		    pstmt.setString(2, description);
    		    pstmt.setString(3, image_url);
    		    
    		    int affectedRows = pstmt.executeUpdate();
    		    if (affectedRows > 0) {
                    try (ResultSet rs = pstmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            categoryId = rs.getInt(1);
                        }
                    }
                }
    	}	
    	return categoryId;
    }

    /**
     * Update category by category id
     * @param name
     * @param description
     * @param image_url
     * @param categoryId
     * @return
     * @throws SQLException
     */
    public int updateCategory(String name, String description, String image_url, int categoryId) throws SQLException {
    	
    	String query = "UPDATE cs_category SET name = ?, description = ?, image_url = ? WHERE category_id = ?";
    	
    	try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
    		    pstmt.setString(1, name);
    		    pstmt.setString(2, description);
    		    pstmt.setString(3, image_url);
                pstmt.setInt(4, categoryId);
    		    
    		    int affectedRows = pstmt.executeUpdate();
    		    if (affectedRows > 0) {
                    try (ResultSet rs = pstmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            categoryId = rs.getInt(1);
                        }
                    }
                }
    	}	
    	return categoryId;
    }

    /**
     * Delete category by category id
     * @param categoryId
     * @return number of affected rows
     * @throws SQLException
     */
    public int deleteCategory(int categoryId) throws SQLException {        
        String query = "DELETE FROM cs_category WHERE category_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, categoryId);
            return pstmt.executeUpdate();
        }
    }
    @Override
    public String toString() {
        return "CategoryDAO []";
    }
}
