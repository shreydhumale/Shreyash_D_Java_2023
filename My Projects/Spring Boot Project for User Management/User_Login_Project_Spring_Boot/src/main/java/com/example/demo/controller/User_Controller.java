package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Role;
import com.example.demo.entity.Users;
import com.example.demo.service.Role_Service;
import com.example.demo.service.User_Service;

@Controller
public class User_Controller {
    
    @Autowired
    private User_Service user_Service;
    
    @Autowired
    private Role_Service role_Service;

    @GetMapping("/save")
    public String saveUser(Model model) {
        model.addAttribute("users", new Users());
        return "create_user";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("users") Users users) {
        user_Service.saveUser(users);
        return "redirect:/getAllUsers"; 
    }

    @GetMapping("/getAllUsers")
    public String getAllUsers(Model model) {
        List<Users> usersList = user_Service.getAllUsersList();
        model.addAttribute("users", usersList);
        return "user_list"; 
    }
    
    @GetMapping("/userDetails")
    public String getUserDetails(@RequestParam("userId") int userId, Model model) {
        Users user = user_Service.getUserById(userId);
        if (user == null) {
            return "redirect:/getAllUsers";
        }
        model.addAttribute("user", user);
        return "user_details";
    }

    @GetMapping("/roleDetails")
    public String getRoleDetails(@RequestParam("roleId") int roleId, Model model) {
        Role role = role_Service.getRoleById(roleId);
        if (role == null) {
            return "redirect:/getAllUsers";
        }
        model.addAttribute("role", role);
        return "role_details";
    }
    
    @GetMapping("/updateUser/{id}")
    public String updateUserForm(@PathVariable("id") int id, Model model) {
        Users user = user_Service.getUserById(id);
        model.addAttribute("users", user);
        return "update_user";
    }
    
    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute Users user) {
        user_Service.saveUser(user);
        return "redirect:/getAllUsers"; 
    }
    
    @GetMapping("/deleteUser/{id}")
    public String deleteUserById(@PathVariable int id) {
    	user_Service.deleteUser(id);
        return "redirect:/getAllUsers"; 
    }
    
}
    



