package com.example.banking.controller;

import com.example.banking.dto.AccountDto;
import com.example.banking.entity.Account;
import com.example.banking.entity.Transaction;
import com.example.banking.mapper.AccountMapper;
import com.example.banking.repository.AccountRepository;
import com.example.banking.repository.TransactionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AdminController(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    // ✅ Get all users (Using AccountMapper)
    @GetMapping("/users")
    public List<AccountDto> getAllUsers() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(AccountMapper::mapToAccountDto) // ✅ Use AccountMapper instead of new AccountDto()
                .collect(Collectors.toList());
    }

    // ✅ Get all transactions
    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // ✅ Delete a user account
    @DeleteMapping("/users/{id}")
    public Map<String, String> deleteUser(@PathVariable Long id) {
        accountRepository.deleteById(id);
        return Map.of("message", "User deleted successfully");
    }
}
