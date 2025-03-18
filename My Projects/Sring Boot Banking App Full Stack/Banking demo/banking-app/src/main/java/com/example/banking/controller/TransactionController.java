package com.example.banking.controller;

import com.example.banking.entity.Transaction;
import com.example.banking.repository.TransactionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/{accountId}")
    public List<Transaction> getTransactions(@PathVariable Long accountId) {
        return transactionRepository.findByAccountIdOrderByTimestampDesc(accountId);
    }
}
