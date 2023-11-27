package com.fab5.bankingapp.service;

import com.fab5.bankingapp.exceptions.NotFoundExceptions.ModelNotFoundExceptions.AccountNotFoundException;
import com.fab5.bankingapp.exceptions.NotFoundExceptions.ModelNotFoundExceptions.DepositNotFoundException;
import com.fab5.bankingapp.model.Account;
import com.fab5.bankingapp.model.Deposit;
import com.fab5.bankingapp.repository.AccountRepository;
import com.fab5.bankingapp.repository.DepositRepository;
import com.fab5.bankingapp.validation.DepositValidation;
import com.fab5.bankingapp.validation.IDValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepositService implements IDValidation<DepositNotFoundException, AccountNotFoundException>, DepositValidation {

    @Autowired
    private DepositRepository depositRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionService transactionService;

    private static final Logger logger = LoggerFactory.getLogger(DepositService.class);


    public void verifyID1(Long id) throws DepositNotFoundException {
        Optional<Deposit> checkDeposit = depositRepository.findById(id);
        if(checkDeposit.isEmpty()){
            logger.error("Error, Deposit ID : " +id+" is not valid");
            throw new DepositNotFoundException(id);
        }
    }
    public void verifyID2(Long id) throws AccountNotFoundException {
        Optional<Account> checkAccount = accountRepository.findById(id);
        if(checkAccount.isEmpty()){
            logger.error("Account ID "+ id + " isn't in the database");
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
        logger.info("Deposit, Account ID, amount are valid, attempting to edit the deposit");
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
    }

    public List<Deposit> getAllDepositsByAccountID(Long accountID){
        verifyID2(accountID);
        logger.info("Running query for account id + " +accountID+ " to find all deposits relating to the account");
        List<Deposit> query = depositRepository.findAllDepositsByAccountID(accountID);
        return query;

    }

}
