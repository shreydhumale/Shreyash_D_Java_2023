package com.example.banking.controller;

import com.example.banking.entity.Account;
import com.example.banking.repository.AccountRepository;
import com.example.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://127.0.0.1:5500") // ✅ Allow frontend requests
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;

    public AuthController(AccountService accountService, PasswordEncoder passwordEncoder, AccountRepository accountRepository) {
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        System.out.println("Login request received for email: " + email);

        Optional<Account> optionalAccount = accountRepository.findByEmail(email); // ✅ Use Optional

        if (optionalAccount.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid email or password"));
        }

        Account account = optionalAccount.get();

        // ✅ Secure password comparison
        if (!passwordEncoder.matches(password, account.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid email or password"));
        }

        return ResponseEntity.ok(Map.of(
            "message", "Login successful",
            "id", account.getId(),
            "email", account.getEmail(),
            "role", account.getRole()
        ));
    }

}
