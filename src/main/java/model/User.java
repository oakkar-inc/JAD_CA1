package model;

import java.util.List;

public class User {
	private int id;
	private String name;	
	private String mobile;
	private String email;
	private String password;
	private int roleId;
	private List<Address> addresses;
	
	public User(int id, String name, String mobile, String email, String password, int roleId) {
		this.id = id;
		this.name = name.trim();
		this.mobile = mobile.trim();
		this.email = email.trim();
		this.password = password.trim();
		this.roleId = roleId;
	}
	
	public int getId() {
        return id;
    }
	
	public void setId(int id) {
        this.id = id;
    }
	
    public String getName() {
        return name;
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
    	return "User [id=" + id + ", name=" + name + ", mobile=" + mobile + ", email=" + email + ", password=" + password + ", roleId=" + roleId + "]";
    }
    
    public List<Address> getAddresses() {
        return addresses;
    }
    
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
