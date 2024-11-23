package model;

import java.sql.Date;

public class Appointment {

    private int appointmentId;
    private int userAddressId;
    private int serviceId;
    private int statusId;
    private Date appointmentDate;
    private String specialRequest;
    private Double rating;
    private String feedback;

    // Default constructor
    public Appointment() {
    }

    // Constructor to initialize all fields
    public Appointment(int appointmentId, int userAddressId, int serviceId, int statusId, Date appointmentDate,
                       String specialRequest, Double rating, String feedback) {
        this.appointmentId = appointmentId;
        this.userAddressId = userAddressId;
        this.serviceId = serviceId;
        this.statusId = statusId;
        this.appointmentDate = appointmentDate;
        this.specialRequest = specialRequest;
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

    public int getUserAddressId() {
        return userAddressId;
    }

    public void setUserAddressId(int userAddressId) {
        this.userAddressId = userAddressId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getSpecialRequest() {
        return specialRequest;
    }

    public void setSpecialRequest(String specialRequest) {
        this.specialRequest = specialRequest;
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

    // toString method to display the Appointment details
    @Override
    public String toString() {
        return "Appointment [appointmentId=" + appointmentId + ", userAddressId=" + userAddressId + ", serviceId=" 
               + serviceId + ", statusId=" + statusId + ", appointmentDate=" + appointmentDate + ", specialRequest=" 
               + specialRequest + ", rating=" + rating + ", feedback=" + feedback + "]";
    }
}
