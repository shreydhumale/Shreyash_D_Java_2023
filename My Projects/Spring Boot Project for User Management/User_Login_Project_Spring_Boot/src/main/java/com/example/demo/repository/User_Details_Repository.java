package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User_details;

@Repository
public interface User_Details_Repository extends JpaRepository<User_details, Integer>{

}
