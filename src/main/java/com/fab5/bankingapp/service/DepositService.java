package com.fab5.bankingapp.service;

import com.fab5.bankingapp.enums.TransactionStatus;
import com.fab5.bankingapp.enums.TransactionType;
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

import java.util.Date;
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

    public Deposit createDeposit(Long account, Deposit deposit) {
        verifyID2(account);
        validateAmount(deposit.getAmount());
        Optional<Account> a = accountRepository.findById(account);
        deposit.setAccount(a.get());
        deposit.setPayee_id(a.get().getId());
        deposit.setStatus(TransactionStatus.PENDING);
        deposit.setTransaction_date(new Date());
        deposit.setType(TransactionType.DEPOSIT);
        depositRepository.save(deposit);
        transactionService.processDeposit(deposit);
        return deposit;
    }

    public void editDeposit(Deposit deposit, Long id){
        verifyID1(id);
        verifyID2(depositRepository.findById(id).get().getAccount().getId());
        validateAmount(deposit.getAmount());

        Deposit oldDeposit = depositRepository.findById(id).get();
        if(oldDeposit!=null && oldDeposit.getStatus()==TransactionStatus.PENDING) {
            if (!deposit.getDescription().equals(null)) {
                oldDeposit.setDescription(deposit.getDescription());
            }
            if (!deposit.getPayee_id().equals(null)) {
                oldDeposit.setPayee_id(deposit.getPayee_id());
            }
            logger.info("amount is valid, attempting to edit the deposit");
        }else{
            throw new RuntimeException("Deposit already went through, cannot edit");
        }
        //We use transactionService here to edit the bank information here and save the information
        transactionService.changeDeposit(deposit,oldDeposit);
        oldDeposit.setAmount(deposit.getAmount());
        oldDeposit.setStatus(TransactionStatus.PENDING);
        depositRepository.save(oldDeposit);
    }
    public Deposit deleteDepositByID(Long id){
        verifyID1(id);
        Deposit deposit = depositRepository.findById(id).get();
        verifyID2(deposit.getAccount().getId());
        if(deposit.getStatus().equals(TransactionStatus.PENDING)) {
            transactionService.deleteDeposit(id);
        }else{
            throw new RuntimeException("Deposit already went throw, you need to withdraw");
        }
        return deposit;
    }

    public List<Deposit> getAllDepositsByAccountID(Long accountID){
        verifyID2(accountID);
        logger.info("Running query for account id + " +accountID+ " to find all deposits relating to the account");
        List<Deposit> query = depositRepository.findAllDepositsByAccountID(accountID);
        return query;

    }

    public List<Deposit> getAllDeposits() {
        return depositRepository.findAll();
    }
}
