package com.fab5.bankingapp.service;

import com.fab5.bankingapp.exceptions.AccountNotFoundException;
import com.fab5.bankingapp.exceptions.DepositNotFoundException;
import com.fab5.bankingapp.model.Account;
import com.fab5.bankingapp.model.Deposit;
import com.fab5.bankingapp.repository.AccountRepository;
import com.fab5.bankingapp.repository.CustomerRepository;
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
    public void verifyID1(Long id) throws DepositNotFoundException {
        Optional<Deposit> checkDeposit = depositRepository.findById(id);
        if(checkDeposit.isEmpty()){
            throw new DepositNotFoundException(id);
        }
    }
    public void verifyID2(Long id) throws AccountNotFoundException {
        Optional<Account> checkAccount = accountRepository.findById(id);
        if(checkAccount.isEmpty()){
            throw new AccountNotFoundException(id);
        }
    }

    public Deposit getDepositByID(Long id){
        verifyID1(id);
        return depositRepository.findById(id).get();
    }


    public void createDeposit(Account account,Deposit deposit){
        Optional<Account> a = accountRepository.findById(account.getId());
        if(a.isEmpty()) {
            throw new AccountNotFoundException(account.getId());
        }
        a.get().setBalance(a.get().getBalance()+deposit.getAmount());
        //Below code will handle adding
        depositRepository.save(deposit);
    }

    public void editDeposit(Deposit deposit, Long id){
        verifyID1(id);
        Deposit oldDeposit = depositRepository.findById(id).get();
        oldDeposit.setAmount(deposit.getAmount());
        oldDeposit.setDescription(deposit.getDescription());
        oldDeposit.setStatus(deposit.getStatus());
        oldDeposit.setPayee_id(deposit.getPayee_id());
        oldDeposit.setTranscation_date(deposit.getTranscation_date());
        depositRepository.save(oldDeposit);
    }

    public void deleteDepositByID(Long id){
        verifyID1(id);
        depositRepository.deleteById(id);
    }

    public List<Deposit> getAllDepositsByAccountID(Long accountID){
        verifyID2(accountID);
        return depositRepository.findAllDepositsByAccountID(accountID);
    }


}
