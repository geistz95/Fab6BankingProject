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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;
import java.net.URI;
import java.util.InvalidPropertiesFormatException;

import static com.fab5.bankingapp.response.DepositResponse.*;

@RestController
public class DepositController {
    @Autowired
    private DepositService depositService;

    private static final Logger logger = LoggerFactory.getLogger(DepositController.class);

    @PostMapping("/accounts/{accountId}/deposits")
    public ResponseEntity<?> createDeposit(@PathVariable Long accountId ,@Valid @RequestBody Deposit deposit){
        HttpHeaders responseHeader = new HttpHeaders();
        //This next line builds the URI link from the deposit
        logger.info("Creating new deposit URI");
        URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(deposit.getDepositId()).toUri();
        responseHeader.setLocation(newPollUri);

        logger.info("Post Request received : creating deposit");
        deposit=depositService.createDeposit(accountId,deposit);
        logger.info("Returning successful post request");

        return createdDepositBuilder(HttpStatus.CREATED, deposit);
    }

    @GetMapping("/deposits/{accountID}/{depositID}")
    public ResponseEntity<?> getDeposit(@PathVariable Long accountID, @PathVariable Long depositID){
        logger.info("Getting Deposit @ "+depositID);
        return getDepositBuilder(HttpStatus.OK,depositService.getDepositByID(depositID).get());
    }

    @DeleteMapping("/deposits/{depositID}")
    public ResponseEntity<?> deleteDeposit(@PathVariable Long depositID){
        logger.info("Delete request received ID : "+depositID);
        Deposit deposit = depositService.deleteDepositByID(depositID);
        logger.info("Deleted deposit ID  : "+depositID);
        return deleteDepositBuilder(HttpStatus.NO_CONTENT, deposit );
    }

    @PutMapping("/deposits/{depositID}")
    public ResponseEntity<?> editDeposit(@Valid @RequestBody Deposit deposit,@PathVariable Long depositID){
        logger.info("Editting deposit ID : "+depositID);
        depositService.editDeposit(deposit,depositID);
        logger.info("Successfully Deleted deposit ID : "+depositID);
        return putDepositBuilder(HttpStatus.ACCEPTED);
    }

    @GetMapping("/accounts/{accountID}/deposits")
    public ResponseEntity<?> getAllAccountDeposits(@PathVariable Long accountID){
        System.out.println("Hello World!");
        logger.info("Getting all deposits from account id  :" + accountID);
        return getAllDepositsBuilder(HttpStatus.OK, depositService.getAllDepositsByAccountID(accountID));
    }

    //things to do : P2P Handling, Bill Handling

}