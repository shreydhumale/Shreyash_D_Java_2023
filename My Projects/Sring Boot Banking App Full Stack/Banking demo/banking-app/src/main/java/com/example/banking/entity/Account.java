package com.example.banking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Table(name = "accounts")
@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "account_holder_name")
	private String accountHolderName;

	@Column(unique = true, nullable = false)
	private String email;

	private String password;

	private String role; // Example values: "USER", "ADMIN"

	@Column(name = "contact_number", unique = true)
	private String contactNumber;

	private double balance;

	public Account() {
	}

	public Account(long id, String accountHolderName, String email, String password, String role, String contactNumber,
			double balance) {
		this.id = id;
		this.accountHolderName = accountHolderName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.contactNumber = contactNumber;
		this.balance = balance;
	}

	// Getters and Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}
