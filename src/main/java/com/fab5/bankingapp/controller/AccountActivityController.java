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


}