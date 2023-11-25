package com.fab5.bankingapp.controller;

import com.fab5.bankingapp.model.AccountActivity;
import com.fab5.bankingapp.service.AccountActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
       // return new ResponseEntity<>(HttpStatus.CREATED);
        /*
        private String activityType;
    private Double amount;
    private LocalDateTime timestamp;
    private Deposit deposit;
    private Withdraw withdraw;
    private  P2PTransfer p2PTransfer;
         */

        return new ResponseEntity<>("Activity (Type: " + accountActivity.getActivityType() +
                                ", Amount: " + accountActivity.getAmount() +
                                ", Timestamp: " + accountActivity.getTimestamp() +
                                ", Deposit:  " + accountActivity.getDeposit() +
                                ", Withdraw: " + accountActivity.getWithdraw() +
                                ", P2PTransfer: " + accountActivity.getP2PTransfer() +
                                ") added to the account.", HttpStatus.CREATED);
    }

    @GetMapping("activities/{accountId}")
    public ResponseEntity<?> getAccountActivities(@PathVariable Long accountId){
        Optional<AccountActivity> activityList = accountActivityService.getAccountActivities(accountId);
        return new ResponseEntity<>("Activities for account with ID " + activityList, HttpStatus.OK);
//        if (activityList.isPresent()) {
//            return new ResponseEntity<>("Activities for account with ID " + accountId + ": " + activityList.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("No activities found for account with ID " + accountId, HttpStatus.OK);
//        }
    }

    @PutMapping("activities/{accountId}")
    public ResponseEntity<?> updateAccountActivities(@RequestBody AccountActivity activity, Long accountId){
        accountActivityService.updateAccountActivities(activity, accountId);
        return new ResponseEntity<>("Activity for account with ID " + accountId + " updated.", HttpStatus.OK);
        //return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<?> deleteAccountActivities(@PathVariable Long accountId){
        accountActivityService.deleteAccountActivities(accountId);
        return new ResponseEntity<>("All activities for account with ID " + accountId + " deleted.", HttpStatus.NO_CONTENT);
        //return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
