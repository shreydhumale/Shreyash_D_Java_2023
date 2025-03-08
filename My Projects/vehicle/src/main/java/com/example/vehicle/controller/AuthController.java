package com.example.vehicle.controller;

import com.example.vehicle.model.User;
import com.example.vehicle.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/auth")  
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            return "Username already exists!";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword())); 
        userRepository.save(user);
        return "User " + user.getUsername() + " registered successfully!";
    }

    @PostMapping("/login")
    public Map<String, String> loginUser(@RequestBody User user) {
        Optional<User> foundUser = userRepository.findByUsername(user.getUsername());

        Map<String, String> response = new HashMap<>();

        if (foundUser.isPresent() && foundUser.get().getPassword().equals(user.getPassword())) {
            response.put("message", "Login successful");
            response.put("username", user.getUsername());
        } else {
            response.put("message", "Invalid username or password");
        }

        return response; 
 
    }
}
