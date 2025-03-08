package com.example.vehicle.controller;

import com.example.vehicle.model.Claim;
import com.example.vehicle.model.User;
import com.example.vehicle.repository.ClaimRepository;
import com.example.vehicle.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/claims")
public class ClaimController {

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private UserRepository userRepository;

    // Owners can see only their claims, Admin can  see all claims
    @GetMapping("/{username}")
    public ResponseEntity<List<Claim>> getClaimsByUser(@PathVariable String username) {
        Optional<User> user = userRepository.findByUsername(username);
        
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of());
        }

        List<Claim> claims;
        if (user.get().getRole().equals("OWNER")) {
            claims = claimRepository.findByUser(user.get());
        } else {
            claims = claimRepository.findAll();
        }

        return ResponseEntity.ok(claims);
    }

    
    @PostMapping("/submit")
    public ResponseEntity<String> submitClaim(@RequestBody Claim claim) {
        Optional<User> user = userRepository.findByUsername(claim.getUser().getUsername());

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }

        if (!user.get().getRole().equals("OWNER")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only vehicle owners can submit claims.");
        }

        claim.setUser(user.get()); 
        claim.setClaimStatus("PENDING");
        claimRepository.save(claim);

        return ResponseEntity.ok("Claim submitted successfully!");
    }

    @PatchMapping("/{claimId}/update-status")
    public ResponseEntity<String> updateClaimStatus(
            @PathVariable Long claimId,
            @RequestParam String status,
            @RequestParam String username) {

        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }

        if (!user.get().getRole().equals("ADMIN") && !user.get().getRole().equals("ADJUSTER")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only Admins and Adjusters can update claims.");
        }

        Optional<Claim> claim = claimRepository.findById(claimId);
        if (claim.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Claim not found!");
        }

        claim.get().setClaimStatus(status.toUpperCase()); 
        claimRepository.save(claim.get());

        return ResponseEntity.ok("Claim status updated successfully!");
    }


}
