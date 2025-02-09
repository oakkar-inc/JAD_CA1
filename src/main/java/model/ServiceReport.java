package model;

public class ServiceReport {
    private int serviceId;
    private String name;
    private int bookingCount;

    public ServiceReport(int serviceId, String name, int bookingCount) {
        this.serviceId = serviceId;
        this.name = name;
        this.bookingCount = bookingCount;
    }

    public int getServiceId() { return serviceId; }
    public String getName() { return name; }
    public int getBookingCount() { return bookingCount; }
}
