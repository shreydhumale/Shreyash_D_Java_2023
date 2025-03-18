package com.example.banking.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AccountDto {
	private Long id;

	@NotBlank(message = "Account Holder Name is required")
	@Size(min = 3, message = "Full Name must be at least 3 characters")
	@NotNull
	private String accountHolderName;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;

	@NotBlank(message = "Password is required")
	@Size(min = 8, message = "Password must be at least 8 characters")
	private String password;

	@NotBlank(message = "Role is required")
	private String role;

	@Pattern(regexp = "\\d{10}", message = "Contact Number must be exactly 10 digits")
	private String contactNumber;

	@Min(value = 100, message = "Initial balance must be at least $100")
	private double balance;

	public AccountDto() {
	}

	public AccountDto(Long id, String accountHolderName, String email, String password, String role,
			String contactNumber, double balance) {
		super();
		this.id = id;
		this.accountHolderName = accountHolderName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.contactNumber = contactNumber;
		this.balance = balance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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