package com.fab5.bankingapp.controller;

import com.fab5.bankingapp.model.Account;
import com.fab5.bankingapp.model.AccountActivity;
import com.fab5.bankingapp.response.AccountActivityResponse;
import com.fab5.bankingapp.service.AccountActivityService;
import com.fasterxml.jackson.annotation.JsonInclude;

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

//@RequestBody("/api/account-activities")
@JsonInclude(JsonInclude.Include.NON_NULL)

public class AccountActivityController {

    private static final Logger logger = LoggerFactory.getLogger(AccountActivityController.class);

    @Autowired
    private AccountActivityService accountActivityService;

    @GetMapping("/activities/{accountId}")
    public ResponseEntity<Object> getAccountActivities(@PathVariable Long accountId) {
        logger.info("Fetching activities for account with ID: {}", accountId);
        List<AccountActivity> activityList = accountActivityService.getAccountActivities(accountId);

        if (!activityList.isEmpty()) {
            logger.info("Activities found for account with ID: {}", accountId);
            return AccountActivityResponse.getActivityBuilder(HttpStatus.OK, activityList);
        } else {
            logger.info("No activities found for account with ID: {}", accountId);
            return AccountActivityResponse.getActivityBuilder(HttpStatus.OK, "No activities found for account with ID " + accountId);
        }
    }



}



