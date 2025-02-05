package model;

import java.util.Date;

public class ServiceHistory {
    private int appointmentId;
    private Date bookDate;
    private String bookingName;
    private String serviceName;
    private double price;
    private String address;
    private String note;
    private String status;
    private String feedback;

    // Constructor
    public ServiceHistory(int appointmentId, Date bookDate, String bookingName, String serviceName, double price,
                          String address, String note, String status, String feedback) {
        this.appointmentId = appointmentId;
        this.bookDate = bookDate;
        this.bookingName = bookingName;
        this.serviceName = serviceName;
        this.price = price;
        this.address = address;
        this.note = note;
        this.status = status;
        this.feedback = feedback;
    }

    // Default Constructor
    public ServiceHistory() {}

    // Getters and Setters
    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Date getDate() {
        return bookDate;
    }

    public void setDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public String getBookingName() {
        return bookingName;
    }

    public void setBookingName(String bookingName) {
        this.bookingName = bookingName;
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
}
