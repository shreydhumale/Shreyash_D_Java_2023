package com.example.vehicle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.vehicle.model.Claim;
import com.example.vehicle.repository.ClaimRepository;
import com.example.vehicle.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")  
public class AdminClaimController {

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/claims")
    public ResponseEntity<?> getAllClaims() {
        List<Claim> claims = claimRepository.findAll();

        if (claims.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList()); 
        }

        return ResponseEntity.ok(claims); 
    }

    @PutMapping("/claims/{id}/status")
    public ResponseEntity<String> updateClaimStatus(@PathVariable Long id, @RequestBody Map<String, String> request) {
        Optional<Claim> claimOptional = claimRepository.findById(id);
        if (claimOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Claim not found");
        }

        Claim claim = claimOptional.get();
        claim.setClaimStatus(request.get("claimStatus"));
        claimRepository.save(claim);

        return ResponseEntity.ok("Claim status updated successfully");
    }

    @DeleteMapping("/claims/{id}")
    public ResponseEntity<String> deleteClaim(@PathVariable Long id) {
        if (!claimRepository.existsById(id)) {
            return ResponseEntity.badRequest().body("Claim not found");
        }

        claimRepository.deleteById(id);
        return ResponseEntity.ok("Claim deleted successfully");
    }
}
