package com.fab5.bankingapp.controller;

import com.fab5.bankingapp.model.AccountActivity;
import com.fab5.bankingapp.service.AccountActivityService;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
//@RequestBody("/api/account-activities")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountActivityController {

    @Autowired
    private AccountActivityService accountActivityService;


    private final Logger logger = LoggerFactory.getLogger(AccountActivityController.class);


    @PostMapping("account-activities/{accountId}")
    public ResponseEntity<?> addAccountActivities(@RequestBody AccountActivity accountActivity){
        logger.info(accountActivity + "Added to account: ");
        accountActivityService.saveAccountActivities(accountActivity);


        logger.info(accountActivity + "successful: ");
        return new ResponseEntity<>("Activity (" +
                                ", Amount: " + accountActivity.getAmount() +
                                ", Timestamp: " + accountActivity.getTimestamp() +
                                ", Transaction:  " + accountActivity.getType() +
                                ") added to the account.", HttpStatus.CREATED);
    }

    @GetMapping("activities/{accountId}")
    public ResponseEntity<?> getAccountActivities(@PathVariable Long accountId){
        logger.info("Fetching activities for account with ID: {}", accountId);
        Optional<AccountActivity> activityList = accountActivityService.getAccountActivities(accountId);
       // return new ResponseEntity<>("Activities for account with ID " + activityList, HttpStatus.OK);

 if (activityList.isPresent()) {
            logger.info("Activities found for account with ID: {}", accountId);
            return new ResponseEntity<>("Activities for account with ID " + accountId + ": " + activityList.get(), HttpStatus.OK);
        } else {
            logger.info("No activities found for account with ID: {}", accountId);
            return new ResponseEntity<>("No activities found for account with ID " + accountId, HttpStatus.OK);
        }

    }

    @PutMapping("activities/{accountId}")
    public ResponseEntity<?> updateAccountActivities(@RequestBody AccountActivity activity, Long accountId){
        logger.info("Updating activity for account with ID: {}", accountId);
        accountActivityService.updateAccountActivities(activity, accountId);
        logger.info("Activity updated successfully for account with ID: {}", accountId);
        return new ResponseEntity<>("Activity for account with ID " + accountId + " updated.", HttpStatus.OK);
        //return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<?> deleteAccountActivities(@PathVariable Long accountId){
        logger.info("Deleting all activities for account with ID: {}", accountId);
        accountActivityService.deleteAccountActivities(accountId);
        logger.info("All activities deleted successfully for account with ID: {}", accountId);
        return new ResponseEntity<>("All activities for account with ID " + accountId + " deleted.", HttpStatus.NO_CONTENT);
        //return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
