package com.example.banking.controller;

import com.example.banking.dto.AccountDto;
import com.example.banking.entity.Account;
import com.example.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;
    

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    // ✅ Create a new account
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
        AccountDto createdAccount = accountService.createAccount(accountDto);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    // ✅ Get account by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable Long id) {
        AccountDto accountDto = accountService.getAccountById(id);

        if (accountDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .header("Content-Type", "application/json")
                    .body(Map.of("error", "User not found"));
        }

        return ResponseEntity.ok(accountDto);
    }


    // ✅ Deposit amount
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> depositAmount(@PathVariable Long id, @RequestBody double amount) {
        AccountDto updatedAccount = accountService.deposit(id, amount);
        return ResponseEntity.ok(updatedAccount);
    }

    // ✅ Withdraw amount
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdrawAmount(@PathVariable Long id, @RequestBody double amount) {
        AccountDto updatedAccount = accountService.withdraw(id, amount);
        return ResponseEntity.ok(updatedAccount);
    }

    // ✅ Get all accounts
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<AccountDto> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    // ✅ Delete account
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted successfully");
    }
    


}
