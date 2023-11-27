package com.fab5.bankingapp.controller;

import com.fab5.bankingapp.model.AccountActivity;
import com.fab5.bankingapp.response.AccountActivityResponse;
import com.fab5.bankingapp.service.AccountActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/api/account-activities")
public class AccountActivityController {

    private static final Logger logger = LoggerFactory.getLogger(AccountActivityController.class);

    @Autowired
    private AccountActivityService accountActivityService;

    @PostMapping("/account-activities/{accountId}")
    public ResponseEntity<?> addAccountActivities(@RequestBody AccountActivity accountActivity, @PathVariable Long accountId) {
        logger.info("Received request to add account activity for account ID: {}", accountId);

        accountActivityService.saveAccountActivities(accountActivity);

        logger.info("Account activity added successfully for account ID: {}", accountId);
        return AccountActivityResponse.createdActivityBuilder(HttpStatus.CREATED, accountActivity);
    }

    @GetMapping("/account-activities/{accountId}")
    public ResponseEntity<?> getAccountActivities(@PathVariable Long accountId) {
        logger.info("Received request to fetch account activities for account ID: {}", accountId);

        Optional<AccountActivity> activityList = accountActivityService.getAccountActivities(accountId);

        if (activityList.isPresent()) {
            logger.info("Found account activities for account ID {}: {}", accountId, activityList.get());
        } else {
            logger.info("No account activities found for account ID: {}", accountId);
        }

        return AccountActivityResponse.getActivityBuilder(HttpStatus.OK, activityList.orElse(null));
    }

    @PutMapping("/account-activities/{accountId}")
    public ResponseEntity<?> updateAccountActivities(@RequestBody AccountActivity activity, @PathVariable Long accountId) {
        logger.info("Received request to update account activity for account ID: {}", accountId);

        accountActivityService.updateAccountActivities(activity, accountId);

        logger.info("Account activity updated successfully for account ID: {}", accountId);
        return AccountActivityResponse.putActivityBuilder(HttpStatus.OK);
    }

    @DeleteMapping("{accountId}")
    public ResponseEntity<?> deleteAccountActivities(@PathVariable Long accountId) {
        logger.info("Received request to delete account activities for account ID: {}", accountId);

        Optional<AccountActivity> activity = accountActivityService.getAccountActivities(accountId);
        accountActivityService.deleteAccountActivities(accountId);

        if (activity.isPresent()) {
            logger.info("Deleted account activities successfully for account ID: {}", accountId);
        } else {
            logger.info("No account activities found for account ID: {}", accountId);
        }

        return AccountActivityResponse.deleteActivityBuilder(HttpStatus.NO_CONTENT, activity);
    }
}