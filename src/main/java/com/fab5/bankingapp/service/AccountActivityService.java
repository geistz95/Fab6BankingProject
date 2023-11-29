package com.fab5.bankingapp.service;

import com.fab5.bankingapp.exceptions.NotFoundExceptions.ModelNotFoundExceptions.AccountActivityNotFoundException;
import com.fab5.bankingapp.exceptions.NotFoundExceptions.ModelNotFoundExceptions.AccountNotFoundException;
import com.fab5.bankingapp.model.Account;
import com.fab5.bankingapp.model.AccountActivity;
import com.fab5.bankingapp.repository.AccountActivityRepository;
import com.fab5.bankingapp.repository.AccountRepository;
import com.fab5.bankingapp.validation.IDValidation;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountActivityService implements IDValidation<AccountActivityNotFoundException, AccountNotFoundException> {

    @Autowired
    private AccountActivityRepository accountActivityRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void verifyID1(Long id) throws AccountActivityNotFoundException {
        Optional<AccountActivity> checkAccountActivity = accountActivityRepository.findById(id);
        if(checkAccountActivity.isEmpty()) {
            throw new AccountActivityNotFoundException(id);
        }
    }

    @Override
    public void verifyID2(Long id) throws AccountNotFoundException {
        Optional<Account> checkAccount = accountRepository.findById(id);
        if(checkAccount.isEmpty()) {
            throw new AccountNotFoundException(id);
        }
    }

    public void saveAccountActivities(AccountActivity accountActivity){
        accountActivityRepository.save(accountActivity);
    }

    public List<AccountActivity> getAccountActivities(Long accountId){
        verifyID2(accountId);
        return accountActivityRepository.findByAccountId(accountId);
    }

    public void updateAccountActivities(AccountActivity updatedActivity, Long accountId) {
        // Assuming the accountId is used to identify the account associated with the activity
        // You may need additional logic depending on your requirements

        // Fetch the existing activity by its ID
        AccountActivity existingActivity = accountActivityRepository.findById(updatedActivity.getActivityId())
                .orElseThrow(() -> new ResourceNotFoundException("Activity not found with ID: " + updatedActivity.getActivityId()));

        // Update the fields with new values
        existingActivity.setAmount(updatedActivity.getAmount());
        existingActivity.setTimestamp(updatedActivity.getTimestamp());
        existingActivity.setType(updatedActivity.getType());

        // Save the updated activity
        accountActivityRepository.save(existingActivity);
    }

}
