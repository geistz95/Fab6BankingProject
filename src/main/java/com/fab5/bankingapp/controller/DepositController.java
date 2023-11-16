package com.fab5.bankingapp.controller;

import com.fab5.bankingapp.model.Deposit;
import com.fab5.bankingapp.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class DepositController {
    @Autowired
    private DepositService depositService;

    @PostMapping("/deposits")
    public ResponseEntity<?> createDeposit(@Valid @RequestBody Deposit deposit) {
        HttpHeaders responseHeader = new HttpHeaders();
        //This next line builds the URI link from the deposit
        URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(deposit.getDepositId()).toUri();
        responseHeader.setLocation(newPollUri);
        depositService.createDeposit(deposit);
        return new ResponseEntity<>(deposit,responseHeader, HttpStatus.CREATED);
    }

    @GetMapping("/deposits/{accountID}/{depositID}")
    public ResponseEntity<?> getDeposit(@PathVariable Long accountsID, @PathVariable Long depositID){
        return new ResponseEntity<>(depositService.getDepositByID(depositID), HttpStatus.OK);
    }

    @DeleteMapping("/deposits/{depositID}")
    public ResponseEntity<?> deleteDeposit(@PathVariable Long depositID){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/deposits/{depositID}")
    public ResponseEntity<?> editDesposit(@Valid @RequestBody Deposit deposit,@PathVariable Long id){
        depositService.editDeposit(deposit,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/customers/{customerID}/deposits")
    public ResponseEntity<?> getAllCustomerDeposits(@PathVariable Long customerID){
        return new ResponseEntity<>(depositService.getAllDepositsByCustomerID(customerID),HttpStatus.OK);
    }

}