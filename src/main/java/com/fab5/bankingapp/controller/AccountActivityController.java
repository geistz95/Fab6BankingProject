package com.fab5.bankingapp.controller;

import com.fab5.bankingapp.enums.TransactionType;
import com.fab5.bankingapp.model.Account;
import com.fab5.bankingapp.model.AccountActivity;
import com.fab5.bankingapp.model.Deposit;
import com.fab5.bankingapp.model.Withdraw;
import com.fab5.bankingapp.response.AccountActivityResponse;
import com.fab5.bankingapp.service.AccountActivityService;
import com.fab5.bankingapp.service.AccountService;
import com.fab5.bankingapp.service.DepositService;
import com.fab5.bankingapp.service.WithdrawService;
import com.fasterxml.jackson.annotation.JsonInclude;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController

//@RequestBody("/api/account-activities")
@JsonInclude(JsonInclude.Include.NON_NULL)

public class AccountActivityController {

    private static final Logger logger = LoggerFactory.getLogger(AccountActivityController.class);


    @Autowired
    private AccountActivityService accountActivityService;


    @GetMapping("/activities/{accountId}")
    public ResponseEntity<?> getAccountActivities(@PathVariable Long accountId) {
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

    /*
    @GetMapping("/activities")
public ResponseEntity<?> getAccountActivities() {
    logger.info("Fetching all activities");
    List<AccountActivity> activityList = accountActivityService.getAccountActivities();

    if (!activityList.isEmpty()) {
        logger.info("Activities found");
        return AccountActivityResponse.getActivityBuilder(HttpStatus.OK, activityList);
    } else {
        logger.info("No activities found");
        return AccountActivityResponse.getActivityBuilder(HttpStatus.OK, "No activities found");
    }
}
     */

//    @GetMapping("/activities/{accountId}")
//    public ResponseEntity<?> getAccountActivities(@PathVariable Long accountId) {
//        logger.info("Fetching activities for account with ID: {}", accountId);
//        List<AccountActivity> activityList = accountActivityService.getAccountActivities(accountId);
//
//        if (!activityList.isEmpty()) {
//            logger.info("Activities found for account with ID: {}", accountId);
//            return new ResponseEntity<>(activityList, HttpStatus.OK);
//        } else {
//            logger.info("No activities found for account with ID: {}", accountId);
//            return new ResponseEntity<>("No activities found for account with ID " + accountId, HttpStatus.OK);
//        }
//    }

    /*
    @GetMapping("/activities/{accountId}")
public ResponseEntity<?> getAccountActivities(@PathVariable Long accountId) {
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
     */

//    @GetMapping("/activities/{accountId}")
//    public ResponseEntity<?> getAccountActivities(@PathVariable Long accountId) {
//        logger.info("Fetching activities for account with ID: {}", accountId);
//        Account accounts = accountService.getAccountById(accountId).orElse(null);
//        Iterable<Withdraw> withdraws = withdrawService.getAllWithdrawalsByAccount(accountId);
//        List<Deposit> deposits = depositService.getAllDepositsByAccountID(accountId);
//        List<TransactionType> transactionTypes = new ArrayList<>();
//        transactionTypes.add(withdraws);
//        List<AccountActivity> activityList = accountActivityService.getAccountActivities(accountId);
//
//        if (!activityList.isEmpty()) {
//            logger.info("Activities found for account with ID: {}", accountId);
//            return AccountActivityResponse.getActivityBuilder(HttpStatus.OK, activityList);
//        } else {
//            logger.info("No activities found for account with ID: {}", accountId);
//            return AccountActivityResponse.getActivityBuilder(HttpStatus.OK, "No activities found for account with ID " + accountId);
//        }
//    }

    @GetMapping("/activities")
    public ResponseEntity<?> getAllActivities(){
        logger.info("Fetching all account activities ");
        List<AccountActivity> activityList = accountActivityService.getAllActivities();
        if (!activityList.isEmpty()) {
            logger.info("Activities found: " );
            return AccountActivityResponse.getAllActivityBuilder(HttpStatus.OK, activityList);
        } else {
            logger.info("No activities found: " );
            return AccountActivityResponse.getAllActivityBuilder(HttpStatus.OK, "No activities found " + activityList);
        }
    }

    /*
    @GetMapping("/activities")
public ResponseEntity<?> getAccountActivities() {
    logger.info("Fetching all activities");
    List<AccountActivity> activityList = accountActivityService.getAccountActivities();

    if (!activityList.isEmpty()) {
        logger.info("Activities found");
        return AccountActivityResponse.getActivityBuilder(HttpStatus.OK, activityList);
    } else {
        logger.info("No activities found");
        return AccountActivityResponse.getActivityBuilder(HttpStatus.OK, "No activities found");
    }
}
     */

    /*
    @GetMapping("/activities")
public ResponseEntity<?> getAccountActivities() {
    logger.info("Fetching all activities");
    List<AccountActivity> activityList = accountActivityService.getAccountActivities();

    if (!activityList.isEmpty()) {
        logger.info("Activities found");
        return AccountActivityResponse.getActivityBuilder(HttpStatus.OK, activityList);
    } else {
        logger.info("No activities found");
        return AccountActivityResponse.getActivityBuilder(HttpStatus.OK, "No activities found");
    }
}
     */

    /*
    @GetMapping("/activities")
public ResponseEntity<?> getAllActivities(){
    logger.info("Fetching all account activities");
    List<AccountActivity> activityList = accountActivityService.getAllActivities();

    if (!activityList.isEmpty()) {
        logger.info("Activities found");
        return AccountActivityResponse.getAllActivityBuilder(HttpStatus.OK, activityList);
    } else {
        logger.info("No activities found");
        return AccountActivityResponse.getAllActivityBuilder(HttpStatus.OK, "No activities found");
    }
}
     */





}



