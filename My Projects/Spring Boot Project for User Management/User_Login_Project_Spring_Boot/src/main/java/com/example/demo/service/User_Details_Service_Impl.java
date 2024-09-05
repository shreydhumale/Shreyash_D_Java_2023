package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User_details;
import com.example.demo.repository.User_Details_Repository;

import jakarta.transaction.Transactional;

@Service
public class User_Details_Service_Impl implements User_Details_Service{
	
	@Autowired
	private User_Details_Repository user_Details_Repository;

	@Override
	@Transactional
	public User_details saveUser_details(User_details user_details) {
		return user_Details_Repository.save(user_details);
	}
	
}
