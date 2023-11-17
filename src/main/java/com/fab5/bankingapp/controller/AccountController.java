package com.fab5.bankingapp.controller;

import com.fab5.bankingapp.exceptions.AccountNotFoundException;
import com.fab5.bankingapp.model.Account;
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

@Controller
public class AccountController implements IDValidation<AccountNotFoundException> {
    @Autowired
    private AccountService accountService;
    @Override
    public void verifyID(Long id) throws AccountNotFoundException {
        Optional<Account> checkAccount = accountService.getAccountById(id);
        if (checkAccount.isEmpty()) {
            throw new AccountNotFoundException(id);
        }
    }
    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Account>> getAllAccounts() {
        Iterable<Account> accounts = accountService.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<?> getAccountById(@PathVariable Long accountId) {
        Optional account = accountService.getAccountById(accountId);
        if (account != null) {
            return new ResponseEntity<>(account, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/customers/{customerId}/accounts")
    public ResponseEntity<List<Account>> getAccountsByCustomerId(@PathVariable Long customerId) {
        List<Account> accounts = accountService.getAccountsByCustomerId(customerId);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @PostMapping("/customers/{customerId}/accounts")
    public ResponseEntity<Account> createAccount(@PathVariable Long customerId, @RequestBody Account account) {
        Account createdAccount = accountService.createAccount(account, customerId);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

//    @PutMapping("/{accountId}")
//    public ResponseEntity<String> updateAccount(@PathVariable Long accountId, @RequestBody Account account) {
//        boolean isUpdated = accountService.updateAccount(accountId, account);
//        if (isUpdated) {
//            return new ResponseEntity<>("Account updated", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Error updating account", HttpStatus.NOT_FOUND);
//        }
//    }
    @PutMapping("/{accountId}")
    public ResponseEntity<String> updateAccount(@PathVariable Long accountId, @RequestBody Account account) {
        Optional<Account> updatedAccount = accountService.updateAccount(accountId, account);
        return updatedAccount
                .map(a -> ResponseEntity.ok("Account updated"))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error updating account"));
    }


    @GetMapping("/{accountId}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long accountId) {
        Optional<Account> account = accountService.deleteAccount(accountId);

        if (account.isPresent()) {
            return new ResponseEntity<>("Account successfully deleted", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Account does not exist", HttpStatus.NOT_FOUND);
        }
    }

}
