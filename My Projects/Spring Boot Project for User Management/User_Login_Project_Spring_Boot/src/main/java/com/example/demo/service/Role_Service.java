package com.example.demo.service;

import com.example.demo.entity.Role;

public interface Role_Service {
    Role getRoleById(int roleId);
    Role saveRole(Role role);
}
