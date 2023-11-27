package com.fab5.bankingapp.service;

import com.fab5.bankingapp.exceptions.NotFoundExceptions.ModelNotFoundExceptions.AccountActivityNotFoundException;
import com.fab5.bankingapp.exceptions.NotFoundExceptions.ModelNotFoundExceptions.AccountNotFoundException;
import com.fab5.bankingapp.model.Account;
import com.fab5.bankingapp.model.AccountActivity;
import com.fab5.bankingapp.repository.AccountActivityRepository;
import com.fab5.bankingapp.repository.AccountRepository;
import com.fab5.bankingapp.validation.IDValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Optional<AccountActivity> getAccountActivities(Long accountId){
        verifyID2(accountId);
        return accountActivityRepository.findByAccountId(accountId);
    }

    public void updateAccountActivities(AccountActivity activity, Long accountId){
        verifyID2(accountId);
        Optional<AccountActivity> activities = accountActivityRepository.findByAccountId(accountId);
        if (activities.isPresent()){
            AccountActivity existingActivity = activities.get();
          //  existingActivity.setAccountId(accountId).newAccountActivity.getAccountId;
            existingActivity.setAccountId(activity.getAccountId());
            existingActivity.setType(activity.getType());
            existingActivity.setAmount(activity.getAmount());
            existingActivity.setTimestamp(activity.getTimestamp());
            existingActivity.setActivityId(activity.getActivityId());

            accountActivityRepository.save(existingActivity);
            Optional.of(existingActivity);

            // accountActivity.setAccountId()
        }   else {
        // Return empty optional if the account with the given ID doesn't exist
         Optional.empty();
    }
    }

    public void deleteAccountActivities(Long activityId){
        verifyID1(activityId);
        accountActivityRepository.deleteById(activityId);
    }

}
