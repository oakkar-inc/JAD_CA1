package model;

import java.sql.Date;

public class Appointment {

    private int appointmentId;
    private int userId;
    private int serviceId;  // New field for serviceId
    private int categoryId; // New field for categoryId
    private String bookingName;
    private int statusId;  
    private String bookingPhone;
    private int addressId;
    private String specialRequest;
    private Date appointmentDate;
    private Double rating;  // Rating can be nullable, so it remains a Double
    private String feedback; // Feedback can be nullable, so it's a String

    // Default constructor
    public Appointment() {
    }

    // Constructor to initialize all fields
    public Appointment(int appointmentId, int userId, int serviceId, int categoryId, String bookingName, int statusId,
                       String bookingPhone, int addressId, String specialRequest, Date appointmentDate, Double rating, String feedback) {
        this.appointmentId = appointmentId;
        this.userId = userId;
        this.serviceId = serviceId; 
        this.categoryId = categoryId;
        this.bookingName = bookingName;
        this.statusId = statusId;
        this.bookingPhone = bookingPhone;
        this.addressId = addressId;
        this.specialRequest = specialRequest;
        this.appointmentDate = appointmentDate;
        this.rating = rating;
        this.feedback = feedback;
    }

    // Getter and Setter methods
    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getBookingName() {
        return bookingName;
    }

    public void setBookingName(String bookingName) {
        this.bookingName = bookingName;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getBookingPhone() {
        return bookingPhone;
    }

    public void setBookingPhone(String bookingPhone) {
        this.bookingPhone = bookingPhone;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getSpecialRequest() {
        return specialRequest;
    }

    public void setSpecialRequest(String specialRequest) {
        this.specialRequest = specialRequest;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "Appointment [appointmentId=" + appointmentId + ", userId=" + userId + ", serviceId=" + serviceId +
                ", categoryId=" + categoryId + ", bookingName=" + bookingName + ", statusId=" + statusId +
                ", bookingPhone=" + bookingPhone + ", addressId=" + addressId + ", specialRequest=" + specialRequest +
                ", appointmentDate=" + appointmentDate + ", rating=" + rating + ", feedback=" + feedback + "]";
    }
}
