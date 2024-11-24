package model;

import java.sql.Date;
import java.sql.Timestamp;

public class Appointment {

    private int appointmentId;
    private int userId;
    private int serviceId;
    private String bookingName;
    private int statusId;
    private String bookingPhone;
    private int addressId;
    private String specialRequest;
    private Date bookDate; 
    private String feedback;
    private Timestamp createdAt;

    // Default constructor
    public Appointment() {
    }

    // Constructor to initialize all fields
    public Appointment(int appointmentId, int userId, int serviceId, String bookingName, int statusId,
                       String bookingPhone, int addressId, String specialRequest, Date bookDate,
                       String feedback, Timestamp createdAt) {
        this.appointmentId = appointmentId;
        this.userId = userId;
        this.serviceId = serviceId;
        this.bookingName = bookingName;
        this.statusId = statusId;
        this.bookingPhone = bookingPhone;
        this.addressId = addressId;
        this.specialRequest = specialRequest;
        this.bookDate = bookDate;  
        this.feedback = feedback;
        this.createdAt = createdAt;
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

    public Date getBookDate() {  
        return bookDate;
    }

    public void setBookDate(Date bookDate) {  
        this.bookDate = bookDate;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Appointment [appointmentId=" + appointmentId + ", userId=" + userId + ", serviceId=" + serviceId +
                ", bookingName=" + bookingName + ", statusId=" + statusId + ", bookingPhone=" + bookingPhone +
                ", addressId=" + addressId + ", specialRequest=" + specialRequest +
                ", bookDate=" + bookDate + ", feedback=" + feedback +  // Updated here
                ", createdAt=" + createdAt + "]";
    }
}
