package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.entity.Role;
import com.example.demo.repository.Role_Repository;

@Service
public class Role_Service_Impl implements Role_Service {

    @Autowired
    private Role_Repository role_Repository;

    @Override
    @Transactional
    public Role getRoleById(int roleId) {
        return role_Repository.findById(roleId).orElse(null);
    }

    @Override
    @Transactional
    public Role saveRole(Role role) {
        return role_Repository.save(role);
    }
}
