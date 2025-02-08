package model;

import java.util.Date;

public class ServiceHistory {
    private int appointmentId;
    private Date bookingDate;
    private String bookingName;
    private int serviceId;
    private String serviceName;
    private double price;
    private String address;
    private String note;
    private String status;
    private String feedback;
    private Date serviceDate;

    // Constructor
    public ServiceHistory(int appointmentId, Date bookingDate, String bookingName, int serviceId, String serviceName, double price,
                          String address, String note, String status, String feedback, Date serviceDate) {
        this.appointmentId = appointmentId;
        this.bookingDate = bookingDate;
        this.bookingName = bookingName;
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.price = price;
        this.address = address;
        this.note = note;
        this.status = status;
        this.feedback = feedback;
        this.serviceDate = serviceDate;
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
}
