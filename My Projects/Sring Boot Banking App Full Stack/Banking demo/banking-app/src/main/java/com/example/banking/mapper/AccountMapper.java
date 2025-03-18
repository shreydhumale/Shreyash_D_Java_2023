package com.example.banking.mapper;

import com.example.banking.dto.AccountDto;
import com.example.banking.entity.Account;

public class AccountMapper {

    // Convert AccountDto to Account (Entity) - ID should NOT be set for new accounts
    public static Account mapToAccount(AccountDto accountDto) {
        Account account = new Account();
        account.setAccountHolderName(accountDto.getAccountHolderName());
        account.setEmail(accountDto.getEmail());
        account.setPassword(accountDto.getPassword());
        account.setRole(accountDto.getRole());
        account.setContactNumber(accountDto.getContactNumber());
        account.setBalance(accountDto.getBalance());

        return account;
    }

    // Convert Account (Entity) to AccountDto - ID is now included
    public static AccountDto mapToAccountDto(Account account) {
        return new AccountDto(
            account.getId(),  // Now, we include the generated ID
            account.getAccountHolderName(),
            account.getEmail(),
            "********",  // Hide password in response
            account.getRole(),
            account.getContactNumber(),
            account.getBalance()
        );
    }
}
