package com.example.banking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.banking.dto.AccountDto;
import com.example.banking.entity.Account;
import com.example.banking.entity.Transaction;
import com.example.banking.mapper.AccountMapper;
import com.example.banking.repository.AccountRepository;
import com.example.banking.repository.TransactionRepository;
import com.example.banking.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	private PasswordEncoder passwordEncoder;

	private AccountRepository accountRepository;
	
	private TransactionRepository transactionRepository;


	public AccountServiceImpl(PasswordEncoder passwordEncoder, AccountRepository accountRepository,
			TransactionRepository transactionRepository) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.accountRepository = accountRepository;
		this.transactionRepository = transactionRepository;
	}

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
	    Account account = new Account();
	    account.setAccountHolderName(accountDto.getAccountHolderName());
	    account.setEmail(accountDto.getEmail());

	    // ✅ Hash the password before saving
	    account.setPassword(passwordEncoder.encode(accountDto.getPassword()));

	    account.setRole(accountDto.getRole());
	    account.setBalance(accountDto.getBalance());

	    accountRepository.save(account);
	    return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto getAccountById(Long id) {
		Account account = accountRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exists"));
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposit(Long id, double amount) {
		//to check if the account exists or not
		Account account = accountRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exists"));
		
		double total = account.getBalance() + amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		
        transactionRepository.save(new Transaction(account, "DEPOSIT", amount));
        
		return AccountMapper.mapToAccountDto(savedAccount);
		
		
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {
		//to check if the account exists or not
		
		Account account = accountRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exists"));
		
		if(account.getBalance() < amount) {
			throw new RuntimeException("Insufficient amount");
		}
		
			
		double total = account.getBalance() - amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		
        transactionRepository.save(new Transaction(account, "WITHDRAWAL", amount));

		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
		.collect(Collectors.toList());
	}

	@Override
	public void deleteAccount(Long id) {
		
		Account account = accountRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exists"));
		
		accountRepository.deleteById(id);
	}
	 @Override
	    public Account findByEmail(String email) {
	        return accountRepository.findByEmail(email).orElse(null);
	    }
}
