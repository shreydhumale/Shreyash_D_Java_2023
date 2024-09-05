package com.example.demo.entity;



import jakarta.persistence.CascadeType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Users {


    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    private String username;
    private String user_password;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User_details user_details;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;
    
    
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Users(int user_id, String username, String user_password, User_details user_details, Role role) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.user_password = user_password;
		this.user_details = user_details;
		this.role = role;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public User_details getUser_details() {
		return user_details;
	}

	public void setUser_details(User_details user_details) {
		this.user_details = user_details;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Users [user_id=" + user_id + ", username=" + username + ", user_password=" + user_password
				+ ", user_details=" + user_details + ", role=" + role + "]";
	}

    
	
}
