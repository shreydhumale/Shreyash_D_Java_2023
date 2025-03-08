package com.example.vehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vehicle.model.Claim;
import com.example.vehicle.model.User;

import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
    List<Claim> findByUser(User user);
}