package model;

import java.util.Date;

public class ServiceHistory {
    private int userId;
    private String email;
    private int appointmentId;
    private Date bookingDate;
    private String bookingName;
    private int serviceId;
    private String serviceName;
    private double price;
    private String address;
    private String note;
    private int statusId;
    private String status;
    private String feedback;
    private Date serviceDate;
    private int helperId;

    // Constructor
    public ServiceHistory(int userId, String email, int appointmentId, Date bookingDate, String bookingName, int serviceId, 
                            String serviceName, double price, String address, String note, int statusId, String status, 
                            String feedback, Date serviceDate, int helperId) {
        this.userId = userId;
        this.email = email;
        this.appointmentId = appointmentId;
        this.bookingDate = bookingDate;
        this.bookingName = bookingName;
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.price = price;
        this.address = address;
        this.note = note;
        this.statusId = statusId;
        this.status = status;
        this.feedback = feedback;
        this.serviceDate = serviceDate;
        this.helperId = helperId;
    }

    // Default Constructor
    public ServiceHistory() {}

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingName() {
        return bookingName;
    }

    public void setBookingName(String bookingName) {
        this.bookingName = bookingName;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public int getHelperId() {
        return helperId;
    }

    public void setHelperId(int helperId) {
        this.helperId = helperId;
    }
}
