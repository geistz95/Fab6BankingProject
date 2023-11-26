package com.fab5.bankingapp.controller;

import com.fab5.bankingapp.model.Account;
import com.fab5.bankingapp.model.Deposit;
import com.fab5.bankingapp.service.DepositService;
import com.fab5.bankingapp.validation.DepositValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.InvalidPropertiesFormatException;

import static com.fab5.bankingapp.response.DepositResponse.*;

@RestController
public class DepositController {
    @Autowired
    private DepositService depositService;

    @PostMapping("/accounts/{accountId}/deposits")
    public ResponseEntity<?> createDeposit(@PathVariable Long accountId ,@Valid @RequestBody Deposit deposit){
        HttpHeaders responseHeader = new HttpHeaders();
        //This next line builds the URI link from the deposit
        URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(deposit.getDepositId()).toUri();
        responseHeader.setLocation(newPollUri);
        depositService.createDeposit(accountId,deposit);
        return createdDepositBuilder(HttpStatus.CREATED, deposit);
    }

    @GetMapping("/deposits/{accountID}/{depositID}")
    public ResponseEntity<?> getDeposit(@PathVariable Long accountID, @PathVariable Long depositID){
        return getDepositBuilder(HttpStatus.OK,depositService.getDepositByID(depositID).get());
    }

    @DeleteMapping("/deposits/{depositID}")
    public ResponseEntity<?> deleteDeposit(@PathVariable Long depositID){
        depositService.deleteDepositByID(depositID);
        return deleteDepositBuilder(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/deposits/{depositID}")
    public ResponseEntity<?> editDeposit(@Valid @RequestBody Deposit deposit,@PathVariable Long depositID){
        depositService.editDeposit(deposit,depositID);
        return putDepositBuilder(HttpStatus.ACCEPTED);
    }

    @GetMapping("/accounts/{accountID}/deposits")
    public ResponseEntity<?> getAllAccountDeposits(@PathVariable Long accountID){
//        System.out.println("Hello World!");
        return getAllDepositsBuilder(HttpStatus.OK, depositService.getAllDepositsByAccountID(accountID));
    }

    //things to do : P2P Handling, Bill Handling

}