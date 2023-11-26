package com.fab5.bankingapp.service;

import com.fab5.bankingapp.controller.WithdrawController;
import com.fab5.bankingapp.enums.Medium;
import com.fab5.bankingapp.enums.TransactionStatus;
import com.fab5.bankingapp.enums.TransactionType;
import com.fab5.bankingapp.exceptions.InsufficientFundsException;
import com.fab5.bankingapp.model.*;
import com.fab5.bankingapp.repository.AccountRepository;
import com.fab5.bankingapp.repository.DepositRepository;
import com.fab5.bankingapp.repository.WithdrawRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class TransactionService {

    @Autowired
    private DepositRepository depositRepository;
    @Autowired
    private WithdrawRepository withdrawRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountActivityService accountActivityService;

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    @Transactional
    public void processWithdraw(Withdraw withdraw)throws InsufficientFundsException {
        Account account= withdraw.getAccount();
        if (withdraw.getAmount() > account.getBalance()){
            throw new InsufficientFundsException("Insufficient Funds in the Account");
        }
        account.setBalance(account.getBalance() - withdraw.getAmount());
        withdrawRepository.save(withdraw);
        accountRepository.save(account);
    }
    @Transactional
    public void changeWithdrawal(Withdraw withdrawal, Withdraw oldWithdrawal){
        Account account = withdrawal.getAccount();
        account.setBalance(account.getBalance()-withdrawal.getAmount()+oldWithdrawal.getAmount());
        accountRepository.save(account);
    }

    @Transactional
    public void deleteWithdrawal(Long id) {
        Withdraw withdrawal = withdrawRepository.findById(id).get();
        Account account = withdrawal.getAccount();
        account.setBalance(account.getBalance()+withdrawal.getAmount());
    }


    /**
     * This gets called when createDeposit
     * The logic here is we get the account and we add the amount of the new deposit to the account
     *
     * @param deposit
     */
    @Transactional
    public void processDeposit(Deposit deposit){
        Account account= deposit.getAccount();
        logger.info("Adding deposit to account balance");
        account.setBalance(account.getBalance() + deposit.getAmount());

        logger.info("Creating activity for account");
        AccountActivity depositAccount = new AccountActivity();
        depositAccount.setAmount(deposit.getAmount());
        depositAccount.setAccountId(deposit.getAccount().getId());
        depositAccount.setType(TransactionType.DEPOSIT);
        depositAccount.setTimestamp(new Date().toString());

        accountActivityService.saveAccountActivities(depositAccount);
        depositRepository.save(deposit);
        accountRepository.save(account);
    }


    /**
     * We get the account here through the oldDeposit and then we do the
     * calulcation based on both deposits and the account's balance
     * This gets called when editDeposit is called
     *
     * Verification has already happened when the method is called
     * @param deposit
     * @param oldDeposit
     */
    @Transactional
    public void changeDeposit(Deposit deposit, Deposit oldDeposit){
        Account account = deposit.getAccount();
        logger.info("Changing deposit info");
        account.setBalance(account.getBalance()+deposit.getAmount()-oldDeposit.getAmount());
        accountRepository.save(account);
    }

    /**
     * Same thing from above but we are just deleting the deposit and we only
     * subtract the deposit amount
     * This gets called when deleteDeposit is called
     *
     * @param id - this is the deposit id
     */
    @Transactional
    public void deleteDeposit(Long id) {
        Deposit deposit = depositRepository.findById(id).get();
        Account account = deposit.getAccount();
        logger.info("Cancelling deposit");
        account.setBalance(account.getBalance()-deposit.getAmount());
        deposit.setStatus(TransactionStatus.CANCELLED);
        depositRepository.save(deposit);
    }

    public void processTransfer(P2PTransfer transfer) {
        Account receiver = accountRepository.findById(transfer.getReceiver().getId()).get();
        Account giver = accountRepository.findById(transfer.getGiver().getId()).get();
        Double amount = transfer.getAmount();

        logger.info("Transfering from account id" + giver.getId() + " amount of " + amount + " to acocunt id "+receiver.getId());
        Deposit deposit = new Deposit();
        deposit.setAccount(receiver);
        deposit.setPayee_id(receiver.getId());
        deposit.setAmount(amount);
        deposit.setDescription("Received payment of "+amount+" from account id : " + giver.getId());
        deposit.setMedium(Medium.BALANCE);
        deposit.setTransaction_date(new Date().toString());
        deposit.setType(TransactionType.P2P);
        deposit.setStatus(TransactionStatus.PENDING);
        AccountActivity depositAccount = new AccountActivity();
        depositAccount.setAmount(amount);
        depositAccount.setAccountId(deposit.getAccount().getId());
        depositAccount.setType(TransactionType.DEPOSIT);
        depositAccount.setTimestamp(new Date().toString());

        Withdraw withdraw = new Withdraw();
        withdraw.setAccount(giver);
        withdraw.setPayerId(giver.getId());
        withdraw.setAmount(amount);
        withdraw.setDescription("Giving payment of "+amount+" to account of : " + receiver.getId());
        withdraw.setTransaction_date(new Date().toString());
        withdraw.setMedium(Medium.BALANCE);
        withdraw.setStatus(TransactionStatus.PENDING);
        withdraw.setType(TransactionType.P2P);
        AccountActivity withdrawAccount = new AccountActivity();
        withdrawAccount.setType(TransactionType.WITHDRAW);
        withdrawAccount.setAccountId(withdraw.getAccount().getId());
        withdrawAccount.setAmount(amount);
        withdrawAccount.setTimestamp(new Date().toString());

        logger.info("Adding account activity to both accounts");
        accountActivityService.saveAccountActivities(withdrawAccount);
        accountActivityService.saveAccountActivities(depositAccount);
        transfer.setDeposit(deposit);
        transfer.setWithdraw(withdraw);
        withdrawRepository.save(withdraw);
        depositRepository.save(deposit);
        receiver.setBalance(receiver.getBalance()+amount);
        giver.setBalance(giver.getBalance()-amount);
        accountRepository.save(receiver);
        accountRepository.save(giver);
    }

    public void undoTransfer(P2PTransfer transfer){
        Account giver = transfer.getGiver();
        Account receiver = transfer.getReceiver();
        Deposit deposit = transfer.getDeposit();
        deleteDeposit(deposit.getDepositId());
        Withdraw withdraw = transfer.getWithdraw();
        deleteWithdraw(withdraw.getId());
        logger.info("Undoing P2P transfer");
        withdrawRepository.save(withdraw);
        depositRepository.save(deposit);
        accountRepository.save(receiver);
        accountRepository.save(giver);
    }

    private void deleteWithdraw(Long id) {
        Withdraw withdraw = withdrawRepository.findById(id).get();
        Account account = withdraw.getAccount();
        account.setBalance(account.getBalance()+withdraw.getAmount());
        withdraw.setStatus(TransactionStatus.CANCELLED);
        withdrawRepository.save(withdraw);
    }
}
