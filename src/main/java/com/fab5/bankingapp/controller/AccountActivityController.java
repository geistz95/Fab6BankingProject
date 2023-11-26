package com.fab5.bankingapp.controller;

import com.fab5.bankingapp.model.AccountActivity;
import com.fab5.bankingapp.service.AccountActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
//@RequestBody("/api/account-activities")
public class AccountActivityController {

    @Autowired
    private AccountActivityService accountActivityService;

    @PostMapping("/{accountId}")
    public ResponseEntity<?> addAccountActivities(@RequestBody AccountActivity accountActivity){
        accountActivityService.saveAccountActivities(accountActivity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("activities/{accountId}")
    public ResponseEntity<Optional<AccountActivity>> getAccountActivities(@PathVariable Long accountId){
        Optional<AccountActivity> activityList = accountActivityService.getAccountActivities(accountId);
        return new ResponseEntity<>(activityList, HttpStatus.OK);
    }

    @PutMapping("activities/{accountId}")
    public ResponseEntity<?> updateAccountActivities(@RequestBody AccountActivity activity, Long accountId){
        accountActivityService.updateAccountActivities(activity, accountId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<?> deleteAccountActivities(@PathVariable Long accountId){
        accountActivityService.deleteAccountActivities(accountId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
