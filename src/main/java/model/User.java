package model;

public class User {
	private int id;
	private String firstName;	
	private String lastName;
	private String email;
	private String password;
	
	public User(int id, String firstName, String lastName, String email, String password) {	
		this.id = id;
		this.firstName = firstName.trim();
		this.lastName = lastName.trim();
		this.email = email.trim();
		this.password = password.trim();
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
    
    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }
}
