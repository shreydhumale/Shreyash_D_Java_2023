package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class User_details {
    
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int profile_id;
	    private String full_name;
	    private String address;

	    @OneToOne(mappedBy = "user_details")
	    private Users user;

	public User_details() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User_details(int profile_id, String full_name, String address, Users user) {
		super();
		this.profile_id = profile_id;
		this.full_name = full_name;
		this.address = address;
		this.user = user;
	}

	public int getProfile_id() {
		return profile_id;
	}

	public void setProfile_id(int profile_id) {
		this.profile_id = profile_id;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "User_details [profile_id=" + profile_id + ", full_name=" + full_name + ", address=" + address
				+ ", user=" + user + "]";
	}
    
    
}
