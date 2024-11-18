package model;

public class User {
	private int id;
	private String firstName;	
	private String lastName;
	private String mobile;
	private String email;
	private String password;
	private int roleId;
	
	public User(int id, String firstName, String lastName, String mobile, String email, String password, int roleId) {
		this.id = id;
		this.firstName = firstName.trim();
		this.lastName = lastName.trim();
		this.mobile = mobile.trim();
		this.email = email.trim();
		this.password = password.trim();
		this.roleId = roleId;
	}
	
	public int getId() {
        return id;
    }
	
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getMobile() {
        return mobile;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public int getRoleId() {
        return roleId;
    }
    
    @Override
    public String toString() {
    	return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", mobile=" + mobile + ", email=" + email + ", password=" + password + ", roleId=" + roleId + "]";
    }
}
