package com.fab5.bankingapp.controller;

import com.fab5.bankingapp.exceptions.WithdrawNotFoundException;
import com.fab5.bankingapp.model.Withdraw;
import com.fab5.bankingapp.service.WithdrawService;
import com.fab5.bankingapp.validation.IDValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

@Controller
public class WithdrawController implements IDValidation<WithdrawNotFoundException> {
    @Autowired
    private WithdrawService withdrawService;

    @Override
    public void verifyID(Long id) throws WithdrawNotFoundException {
        Optional<Withdraw> checkWithdraw = withdrawService.getWithdrawById(id);
        if (checkWithdraw.isEmpty()) {
            throw new WithdrawNotFoundException(id);
        }
    }

    @GetMapping(value = "/accounts/{accountId}/withdrawals")
    public Iterable<Withdraw> getAllWithdrawals(@PathVariable Long accountId){
        return withdrawService.getAllWithdrawalsByAccount(accountId);
    }

    @GetMapping(value = "/withdrawals/{withdrawalId}")
    public ResponseEntity<?> getWithdrawalById(@PathVariable Long withdrawalId){
        Optional<Withdraw> w = withdrawService.getWithdrawById(withdrawalId);
        return new ResponseEntity<>(w, HttpStatus.OK);
    }

    @PostMapping(value = "/accounts/{accountId}/withdrawals")
    public ResponseEntity<?> createWithdrawal(@PathVariable Long accountId, @RequestBody Withdraw withdrawal){
        withdrawal = withdrawService.createWithdraw(withdrawal);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").buildAndExpand(withdrawal.getId()).toUri());
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/withdrawals/{withdrawalId}")
    public ResponseEntity<?> updateWithdrawal(@RequestBody Withdraw withdrawal, @PathVariable Long withdrawalId){
        withdrawService.updateWithdraw(withdrawalId,withdrawal);
        return new ResponseEntity<>(withdrawal, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/withdrawals/{withdrawalId}")
    public ResponseEntity<?> deleteWithdrawal(@PathVariable Long withdrawalId){
        withdrawService.deleteWithdrawById(withdrawalId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }








}
