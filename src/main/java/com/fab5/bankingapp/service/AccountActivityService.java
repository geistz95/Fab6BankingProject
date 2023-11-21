package com.fab5.bankingapp.service;

import com.fab5.bankingapp.model.Account;
import com.fab5.bankingapp.model.AccountActivity;
import com.fab5.bankingapp.repository.AccountActivityRepository;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountActivityService {

    @Autowired
    private AccountActivityRepository accountActivityRepository;


    public void saveAccountActivities(AccountActivity accountActivity){
        accountActivityRepository.save(accountActivity);
    }

    public Optional<AccountActivity> getAccountActivities(Long accountId){
        return accountActivityRepository.findByAccountId(accountId);
    }

    public void updateAccountActivities(AccountActivity activity, Long accountId){

        Optional<AccountActivity> activities = accountActivityRepository.findByAccountId(accountId);
        if (activities.isPresent()){
            AccountActivity existingActivity = activities.get();
          //  existingActivity.setAccountId(accountId).newAccountActivity.getAccountId;
            existingActivity.setAccountId(activity.getAccountId());
            existingActivity.setActivityType(activity.getActivityType());
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
        accountActivityRepository.deleteById(activityId);
    }
}
