package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Users;

public interface User_Service {
	public Users saveUser(Users users);
    public List<Users> getAllUsersList();
	Users getUserById(int userId);
	public void updateUser(Users users);
	public void deleteUser(int userID);
}
