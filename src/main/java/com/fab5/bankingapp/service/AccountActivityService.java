package com.fab5.bankingapp.service;

import com.fab5.bankingapp.exceptions.NotFoundExceptions.ModelNotFoundExceptions.AccountActivityNotFoundException;
import com.fab5.bankingapp.exceptions.NotFoundExceptions.ModelNotFoundExceptions.AccountNotFoundException;
import com.fab5.bankingapp.model.Account;
import com.fab5.bankingapp.model.AccountActivity;
import com.fab5.bankingapp.repository.AccountActivityRepository;
import com.fab5.bankingapp.repository.AccountRepository;
import com.fab5.bankingapp.repository.DepositRepository;
import com.fab5.bankingapp.repository.WithdrawRepository;
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
    public void verifyID1(String message, Long id) throws AccountActivityNotFoundException {
        Optional<AccountActivity> checkAccountActivity = accountActivityRepository.findById(id);
        if(checkAccountActivity.isEmpty()) {
            throw new AccountActivityNotFoundException(id);
        }
    }

    @Override
    public void verifyID2(String message, Long id) throws AccountNotFoundException {
        Optional<Account> checkAccount = accountRepository.findById(id);
        if(checkAccount.isEmpty()) {
            throw new AccountNotFoundException(message, id);
        }
    }

    public void saveAccountActivities(AccountActivity accountActivity){
        accountActivityRepository.save(accountActivity);
    }

    public List<AccountActivity> getAccountActivities(Long accountId){
<<<<<<< HEAD
       // verifyID2(accountId);
=======
        verifyID2("error fetching account for account activities", accountId);
>>>>>>> development
        return accountActivityRepository.findByAccountId(accountId);
    }

   public List<AccountActivity> getAllActivities(){

        return accountActivityRepository.findAll();
   }

}
