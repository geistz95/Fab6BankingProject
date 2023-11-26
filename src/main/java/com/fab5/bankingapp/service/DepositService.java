package com.fab5.bankingapp.service;

import com.fab5.bankingapp.exceptions.AccountNotFoundException;
import com.fab5.bankingapp.exceptions.DepositNotFoundException;
import com.fab5.bankingapp.exceptions.InvalidDepositAmount;
import com.fab5.bankingapp.model.Account;
import com.fab5.bankingapp.model.Deposit;
import com.fab5.bankingapp.repository.AccountRepository;
import com.fab5.bankingapp.repository.DepositRepository;
import com.fab5.bankingapp.validation.DepositValidation;
import com.fab5.bankingapp.validation.IDValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DepositService implements IDValidation<DepositNotFoundException, AccountNotFoundException>, DepositValidation {

    @Autowired
    private DepositRepository depositRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionService transactionService;


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

    public Optional<Deposit> getDepositByID(Long id){
        verifyID1(id);
        return depositRepository.findById(id);
    }

    public void createDeposit(Long account, Deposit deposit) {
        verifyID2(account);
        validateAmount(deposit.getAmount());
        Optional<Account> a = accountRepository.findById(account);
        deposit.setAccount(a.get());
        depositRepository.save(deposit);
        transactionService.processDeposit(deposit);
    }

    public void editDeposit(Deposit deposit, Long id){
        verifyID1(id);
        verifyID2(depositRepository.findById(id).get().getAccount().getId());
        validateAmount(deposit.getAmount());
        Deposit oldDeposit = depositRepository.findById(id).get();
        deposit.setAccount(oldDeposit.getAccount());
        oldDeposit.setAmount(deposit.getAmount());
        oldDeposit.setDescription(deposit.getDescription());
        oldDeposit.setStatus(deposit.getStatus());
        oldDeposit.setPayee_id(deposit.getPayee_id());
        oldDeposit.setTransaction_date(deposit.getTransaction_date());
        //We use transactionService here to edit the bank information here and save the information
        transactionService.changeDeposit(deposit,oldDeposit);
        depositRepository.save(oldDeposit);
    }
    public void deleteDepositByID(Long id){
        verifyID1(id);
        verifyID2(depositRepository.findById(id).get().getAccount().getId());
        transactionService.deleteDeposit(id);
        depositRepository.deleteById(id);
    }

    public List<Deposit> getAllDepositsByAccountID(Long accountID){
        verifyID2(accountID);
        List<Deposit> query = depositRepository.findAllDepositsByAccountID(accountID);
        return query;

    }

}
