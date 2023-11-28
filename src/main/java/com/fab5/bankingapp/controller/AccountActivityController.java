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

}
