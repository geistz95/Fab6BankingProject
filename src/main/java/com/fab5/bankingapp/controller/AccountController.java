package com.fab5.bankingapp.controller;

import com.fab5.bankingapp.exceptions.AccountNotFoundException;
import com.fab5.bankingapp.exceptions.CustomerNotFoundException;
import com.fab5.bankingapp.model.Account;
import com.fab5.bankingapp.model.Customer;
import com.fab5.bankingapp.response.AccountResponse;
import com.fab5.bankingapp.service.AccountService;
import com.fab5.bankingapp.validation.IDValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import java.util.Optional;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public ResponseEntity<Object> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return AccountResponse.getAllAccountsBuilder(HttpStatus.OK, accounts);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<?> getAccountById(@PathVariable Long accountId) {
        Optional<Account> account = accountService.getAccountById(accountId);
        return account.map(a -> new ResponseEntity<>(AccountResponse.getAccountBuilder(HttpStatus.OK, a), HttpStatus.OK))
                .orElse(new ResponseEntity<>(AccountResponse.getAccountBuilder(HttpStatus.NOT_FOUND, null), HttpStatus.NOT_FOUND));
    }

    @GetMapping("/customers/{customerId}/accounts")
    public ResponseEntity<Object> getAccountsByCustomerId(@PathVariable Long customerId) {
        List<Account> accounts = accountService.getAccountsByCustomerId(customerId);
        return AccountResponse.getAllAccountsBuilder(HttpStatus.OK, accounts);
    }

    @PostMapping("/customers/{customerId}/accounts")
    public ResponseEntity<Object> createAccount(@PathVariable Long customerId, @RequestBody Account account) {
        Account createdAccount = accountService.createAccount(account, customerId);
        return AccountResponse.createdAccountBuilder(HttpStatus.CREATED, createdAccount);
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<Object> updateAccount(@PathVariable Long accountId, @RequestBody Account account) {
        Optional<Account> updatedAccount = accountService.updateAccount(accountId, account);
        return updatedAccount
                .map(a -> AccountResponse.putAccountBuilder(HttpStatus.OK))
                .orElse(AccountResponse.putAccountBuilder(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("/accounts/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long accountId) {
        Optional<Account> account = accountService.deleteAccount(accountId);

        return account.map(a -> new ResponseEntity<>(AccountResponse.deleteAccountBuilder(HttpStatus.ACCEPTED, Optional.of(a)), HttpStatus.ACCEPTED))
                .orElse(new ResponseEntity<>(AccountResponse.deleteAccountBuilder(HttpStatus.NOT_FOUND, Optional.empty()), HttpStatus.NOT_FOUND));
    }

}
