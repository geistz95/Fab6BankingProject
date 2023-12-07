package com.fab5.bankingapp.controller;

import com.fab5.bankingapp.exceptions.NotFoundExceptions.DataNotFoundExceptions.CustomerHasNoAccountsException;
import com.fab5.bankingapp.exceptions.NotFoundExceptions.DataNotFoundExceptions.NoAccountsException;
import com.fab5.bankingapp.model.Account;
import com.fab5.bankingapp.response.AccountResponse;
import com.fab5.bankingapp.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    private final Logger logger = LoggerFactory.getLogger(AccountController.class);


    public void verifyIfCustomerHasAccounts(String message, Long customerID) throws CustomerHasNoAccountsException {
        List<Account> checkCustomerAccounts = accountService.getAccountsByCustomerId(customerID);
        if (checkCustomerAccounts.isEmpty()) {
            throw new CustomerHasNoAccountsException(message, customerID);
        }
    }

    public void verifyIfAccountsExist(String message) throws NoAccountsException {
        List<Account> checkAllAccounts = accountService.getAllAccounts();
        if (checkAllAccounts.isEmpty()) {
            throw new NoAccountsException(message);
        }
    }

    @GetMapping("/accounts")
    public ResponseEntity<Object> getAllAccounts() {
        verifyIfAccountsExist("error fetching accounts");
        logger.info("Fetching all accounts");
        List<Account> accounts = accountService.getAllAccounts();
        logger.info("Returning all accounts");
        return AccountResponse.getAllAccountsBuilder(HttpStatus.OK, accounts);
    }


    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<?> getAccountById(@PathVariable Long accountId) {
        logger.info("Fetching account by ID: {}", accountId);
        Optional<Account> account = accountService.getAccountById(accountId);
        logger.info("Returning account by ID: {}", accountId + "'s status");
        return(AccountResponse.getAccountBuilder(HttpStatus.OK , account.get() ));
               // .orElse(new ResponseEntity<>(AccountResponse.getAccountBuilder(HttpStatus.NOT_FOUND, null), HttpStatus.NOT_FOUND));
//         if (account != null) {
//        logger.info("Returning account under ID: {}", accountId);
//            return new ResponseEntity<>(AccountResponse.getAccountBuilder(HttpStatus.OK, account), HttpStatus.OK);
//        } else {
//            logger.warn("Account Not Found");
//            return new ResponseEntity<>(AccountResponse.getAccountBuilder(HttpStatus.NOT_FOUND, null), HttpStatus.NOT_FOUND);
//        }

    }

    @GetMapping("/customers/{customerId}/accounts")
    public ResponseEntity<Object> getAccountsByCustomerId(@PathVariable Long customerId) {
        logger.info("Fetching account by customer ID: {}", customerId);
        verifyIfCustomerHasAccounts("Error fetching customers accounts",customerId);
        List<Account> accounts = accountService.getAccountsByCustomerId(customerId);
        logger.info("Accounts under: {}", customerId);
        return AccountResponse.getAllAccountsBuilder(HttpStatus.OK, accounts);
    }

    @PostMapping("/customers/{customerId}/accounts")
    public ResponseEntity<Object> createAccount(@PathVariable Long customerId, @RequestBody Account account) {
        logger.info("Creating account for ID: {}", customerId);
        Account createdAccount = accountService.createAccount(account, customerId);
        logger.info("Account created successfully");
        return AccountResponse.createdAccountBuilder(HttpStatus.CREATED, createdAccount);
    }

    @PutMapping("/accounts/{accountId}")
    public ResponseEntity<Object> updateAccount(@PathVariable Long accountId, @RequestBody Account account) {
        logger.info("Updating account by ID: {}", accountId);
        Optional<Account> updatedAccount = accountService.updateAccount(accountId, account);
//        logger.info("Returning account by ID: {}", accountId);
//        return updatedAccount
//                .map(a -> AccountResponse.putAccountBuilder(HttpStatus.OK))
//                .orElse(AccountResponse.putAccountBuilder(HttpStatus.NOT_FOUND));
     if (updatedAccount != null) {
            logger.info("Account Updated Successfully");
            return AccountResponse.putAccountBuilder(HttpStatus.OK);
        } else {
            logger.warn("Account Not Found");
            return AccountResponse.putAccountBuilder(HttpStatus.NO_CONTENT);
        }

    }


    @DeleteMapping("/accounts/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long accountId) {
        logger.info("Deleting account with ID: {}", accountId);
        Optional<Account> account = accountService.deleteAccount(accountId);

        logger.info("Account deletion status");

//        return account.map(a -> new ResponseEntity<>(AccountResponse.deleteAccountBuilder(HttpStatus.NO_CONTENT, Optional.of(a)), HttpStatus.NO_CONTENT))
//                .orElse(new ResponseEntity<>(AccountResponse.deleteAccountBuilder(HttpStatus.NO_CONTENT, Optional.empty()), HttpStatus.NO_CONTENT));

        return AccountResponse.deleteAccountBuilder(HttpStatus.NO_CONTENT);
     //   return(AccountResponse.deleteAccountBuilder(HttpStatus.NO_CONTENT));
                //.orElse(new ResponseEntity<>(AccountResponse.deleteAccountBuilder(HttpStatus.NO_CONTENT, Optional.empty()), HttpStatus.NO_CONTENT));

    }

}
