package com.fab5.bankingapp.service;

import com.fab5.bankingapp.exceptions.AccountNotFoundException;
import com.fab5.bankingapp.exceptions.DepositNotFoundException;
import com.fab5.bankingapp.model.Account;
import com.fab5.bankingapp.model.Deposit;
import com.fab5.bankingapp.repository.AccountRepository;
import com.fab5.bankingapp.repository.DepositRepository;
import com.fab5.bankingapp.validation.IDValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepositService implements IDValidation<DepositNotFoundException> {

    @Autowired
    private DepositRepository depositRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void verifyID(Long id) throws DepositNotFoundException {

    }
    public void verifyDepositID(Long id){
        Optional<Deposit> checkDeposit = depositRepository.findById(id);
        if(checkDeposit.isEmpty()){
            throw new DepositNotFoundException(id);
        }
    }
    public void verifyAccountId(Long id){
        Optional<Account> checkAccount = accountRepository.findById(id);
        if(checkAccount.isEmpty()){
            throw new AccountNotFoundException(id);
        }
    }



    public Optional<Deposit> getDepositByID(Long id){
        verifyDepositID(id);
        return depositRepository.findById(id);
    }

    public void createDeposit(Deposit deposit){
        depositRepository.save(deposit);
    }

    public void editDeposit(Deposit deposit, Long id){
        verifyDepositID(id);
        Deposit oldDeposit = depositRepository.findById(id).get();
        oldDeposit.setAmount(deposit.getAmount());
        oldDeposit.setDescription(deposit.getDescription());
        oldDeposit.setStatus(deposit.getStatus());
        oldDeposit.setPayee_id(deposit.getPayee_id());
        oldDeposit.setTranscation_date(deposit.getTranscation_date());
        depositRepository.save(oldDeposit);
    }

    public void deleteDeposit(Deposit deposit, Long id){
        verifyDepositID(id);
        depositRepository.delete(deposit);
    }

    public List<Deposit> getAllDepositsByCustomerID(Long accountID){
        verifyAccountId(accountID);
        return depositRepository.findAllDepositsByAccountID(accountID);
    }

}
