package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Role;
import com.example.demo.entity.Users;
import com.example.demo.repository.Role_Repository;
import com.example.demo.repository.User_Details_Repository;
import com.example.demo.repository.User_Repository;

@Service
public class User_Service_Impl implements User_Service {

    @Autowired
    private User_Repository user_Repository;

    @Autowired
    private User_Details_Repository user_Details_Repository;

    @Override
    @Transactional
    public Users saveUser(Users users) {
        return user_Repository.save(users);
    }

    @Override
    public List<Users> getAllUsersList() {
        return user_Repository.findAll();
    }

    @Override
    public Users getUserById(int userId) {
        return user_Repository.findById(userId).orElse(null);
    }

    @Override
	public void updateUser(Users users) {
    	user_Repository.save(users);
	}
    
	@Override
	public void deleteUser(int userID) {
		user_Repository.delete(getUserById(userID));
	}

	
}
