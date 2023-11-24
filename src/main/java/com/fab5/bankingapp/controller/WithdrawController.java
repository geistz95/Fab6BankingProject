package com.fab5.bankingapp.controller;

import com.fab5.bankingapp.exceptions.AccountNotFoundException;
import com.fab5.bankingapp.exceptions.InsufficientFundsException;
import com.fab5.bankingapp.exceptions.WithdrawNotFoundException;
import com.fab5.bankingapp.model.Withdraw;
import com.fab5.bankingapp.response.WithdrawResponse;
import com.fab5.bankingapp.service.TransactionService;
import com.fab5.bankingapp.service.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static com.fab5.bankingapp.response.WithdrawResponse.*;
;

import java.util.Optional;

@RestController
public class WithdrawController {
    @Autowired
    private WithdrawService withdrawService;
    @Autowired
    private TransactionService transactionService;


    @GetMapping(value = "/accounts/{accountId}/withdrawals")
    public ResponseEntity<?> getAllWithdrawals(@PathVariable Long accountId) {
        Iterable<Withdraw> withdraws = withdrawService.getAllWithdrawalsByAccount(accountId);
        return WithdrawResponse.getAllWithdrawalsBuilder(HttpStatus.OK, withdraws);
    }

    @GetMapping(value = "/withdrawals/{withdrawalId}")
    public ResponseEntity<?> getWithdrawalById(@PathVariable Long withdrawalId){
        Optional<Withdraw> w = withdrawService.getWithdrawById(withdrawalId);
        return WithdrawResponse.getWithdrawBuilder(HttpStatus.OK, w);
    }

    @PostMapping(value = "/accounts/{accountId}/withdrawals")
    public ResponseEntity<?> createWithdrawal(@PathVariable Long accountId, @RequestBody Withdraw withdrawal){
        withdrawService.createWithdraw(accountId, withdrawal);
        try{
            transactionService.processWithdraw(withdrawal);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").buildAndExpand(withdrawal.getId()).toUri());
        return WithdrawResponse.createdWithdrawBuilder(HttpStatus.CREATED, responseHeaders);
        }catch (InsufficientFundsException e){
            return new ResponseEntity<>("Insufficient Funds in Account", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/withdrawals/{withdrawalId}")
    public ResponseEntity<?> updateWithdrawal(@RequestBody Withdraw withdrawal, @PathVariable Long withdrawalId){
        withdrawService.updateWithdraw(withdrawalId,withdrawal);
        return WithdrawResponse.putWithdrawalBuilder(HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/withdrawals/{withdrawalId}")
    public ResponseEntity<?> deleteWithdrawal(@PathVariable Long withdrawalId){
        withdrawService.deleteWithdrawById(withdrawalId);
        return WithdrawResponse.deleteWithdrawalBuilder(HttpStatus.NO_CONTENT);
    }








}
