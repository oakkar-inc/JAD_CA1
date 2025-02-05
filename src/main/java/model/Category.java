package model;

public class Category {
	private int categoryId;
	private String name;	
	private String description;
	private String image_url;
	
    public Category(int categoryId, String name, String description, String image_url) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.image_url = image_url;
    }
	
    //getters and setters
    public int getCategoryId() {
    	return categoryId;
    }

    public void setCategoryId(int categoryId) {
    	this.categoryId = categoryId;
    }

    public String getName() {
    	return name;
    }

    public void setName(String name) {
    	this.name = name;
    }

    public String getDescription() {
    	return description;
    }

    public void setDescription(String description) {
    	this.description = description;
    }

    public String getImage_url() {
    	return image_url;
    }

    public void setImage_url(String image_url) {
    	this.image_url = image_url;
    }
}
