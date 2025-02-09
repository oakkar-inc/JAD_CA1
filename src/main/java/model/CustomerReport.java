package model;

public class CustomerReport {
    private int userId;
    private String name;
    private String email;
    private double totalSpent;

    public CustomerReport(int userId, String name, String email, double totalSpent) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.totalSpent = totalSpent;
    }

    public int getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public double getTotalSpent() { return totalSpent; }
}
