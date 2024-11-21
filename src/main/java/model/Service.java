package model;

import org.HdrHistogram.AbstractHistogram;

public class Service {
	private int serviceId;
	private String name;	
	private double price;
	private String details;
    private String image_url;
    private int categoryId;
	
    public Service(int serviceId, String name, double price, String details, String image_url, int categoryId) {
        this.serviceId = serviceId;
        this.name = name;
        this.price = price;
        this.details = details;
        this.image_url=	image_url;
        this.categoryId = categoryId;
    }
	
    //getters and setters
    public int getServiceId() {
    	return serviceId;
    }

    public void setServiceId(int serviceId) {
    	this.serviceId = serviceId;
    }

    public String getName() {
    	return name;
    }

    public void setName(String name) {
    	this.name = name;
    }

    public double getPrice() {
    	return price;
    }

    public void setPrice(double price) {
    	this.price = price;
    }

    public String getDetails() {
    	return details;
    }

    public void setDetails(String details) {
    	this.details = details;
    }

    public String getImage_url() {
    	return image_url;
    }

    public void setImage_url(String image_url) {
    	this.image_url = image_url;
    }

    public int getCategoryId() {
    	return categoryId;
    }

    public void setCategoryId(int categoryId) {
    	this.categoryId = categoryId;
    }
}
