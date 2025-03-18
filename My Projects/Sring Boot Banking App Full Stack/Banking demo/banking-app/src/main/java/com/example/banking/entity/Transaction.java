package com.example.banking.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "account_id", nullable = false)
	private Account account;

	private String type; // "DEPOSIT" or "WITHDRAWAL"
	private double amount;
	private LocalDateTime timestamp;

	public Transaction() {
		this.timestamp = LocalDateTime.now();
	}

	public Transaction(Account account, String type, double amount) {
		this.account = account;
		this.type = type;
		this.amount = amount;
		this.timestamp = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public Account getAccount() {
		return account;
	}

	public String getType() {
		return type;
	}

	public double getAmount() {
		return amount;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}
}
