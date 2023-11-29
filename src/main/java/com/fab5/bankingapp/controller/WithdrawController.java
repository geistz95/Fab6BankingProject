package com.fab5.bankingapp.controller;

import com.fab5.bankingapp.exceptions.InsufficientFundsException;
import com.fab5.bankingapp.exceptions.NotFoundExceptions.DataNotFoundExceptions.AccountHasNoWithdrawalsException;
import com.fab5.bankingapp.exceptions.NotFoundExceptions.DataNotFoundExceptions.CustomerHasNoAccountsException;
import com.fab5.bankingapp.model.Withdraw;
import com.fab5.bankingapp.response.WithdrawResponse;
import com.fab5.bankingapp.service.TransactionService;
import com.fab5.bankingapp.service.WithdrawService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.util.*;

@RestController
public class WithdrawController {
    @Autowired
    private WithdrawService withdrawService;
    @Autowired
    private TransactionService transactionService;
     private static final Logger logger = LoggerFactory.getLogger(WithdrawController.class);
    public void verifyIfAccountHasWithdrawals(Long accountID) throws AccountHasNoWithdrawalsException {

        Iterable<Withdraw> checkAccountWithdrawals = withdrawService.getAllWithdrawalsByAccount(accountID);
        List<Withdraw> withdrawList = new ArrayList<>((Collection) checkAccountWithdrawals);
        if (withdrawList.isEmpty()) {
            throw new AccountHasNoWithdrawalsException(accountID);
        }
    }
    @GetMapping(value = "/accounts/{accountId}/withdrawals")
    public ResponseEntity<?> getAllWithdrawals(@PathVariable Long accountId) {
        logger.info("Request received: Getting All Withdrawals");
        verifyIfAccountHasWithdrawals(accountId);
        Iterable<Withdraw> withdraws = withdrawService.getAllWithdrawalsByAccount(accountId);
        logger.info("All Withdrawals Gotten Successfully");
        return WithdrawResponse.getAllWithdrawalsBuilder(HttpStatus.OK, withdraws);
    }

    @GetMapping(value = "/withdrawals/{withdrawalId}")
    public ResponseEntity<?> getWithdrawalById(@PathVariable Long withdrawalId){
        logger.info("Request received: Getting Withdrawal By Id");
        Optional<Withdraw> w = withdrawService.getWithdrawById(withdrawalId);
        logger.info("Withdrawal Gotten Successfully");
        return WithdrawResponse.getWithdrawBuilder(HttpStatus.OK, w);
    }

    @PostMapping(value = "/accounts/{accountId}/withdrawals")
    public ResponseEntity<?> createWithdrawal(@PathVariable Long accountId, @RequestBody Withdraw withdrawal){
        logger.info("Request received: Creating Withdrawal");

        try{
            withdrawService.createWithdraw(accountId, withdrawal);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").buildAndExpand(withdrawal.getId()).toUri());
        logger.info("Withdrawal Created Successfully");
        return WithdrawResponse.createdWithdrawBuilder(HttpStatus.CREATED, withdrawal);
        }catch (InsufficientFundsException e){
            return new ResponseEntity<>("Insufficient Funds in Account", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/withdrawals/{withdrawalId}")
    public ResponseEntity<?> updateWithdrawal(@RequestBody Withdraw withdrawal, @PathVariable Long withdrawalId){
        logger.info("Request received: Updating Withdrawal");
        withdrawService.updateWithdraw(withdrawalId,withdrawal);
        logger.info("Updated Withdrawal Successfully");
        return WithdrawResponse.putWithdrawalBuilder(HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/withdrawals/{withdrawalId}")
    public ResponseEntity<?> deleteWithdrawal(@PathVariable Long withdrawalId){
        logger.info("Request received: Deleting Withdrawal");
        withdrawService.deleteWithdrawById(withdrawalId);
        logger.info("Withdrawal Deleted Successfully");
        return WithdrawResponse.deleteWithdrawalBuilder(HttpStatus.NO_CONTENT);
    }








}
