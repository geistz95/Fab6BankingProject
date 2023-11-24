package com.fab5.bankingapp.service;

import com.fab5.bankingapp.exceptions.InsufficientFundsException;
import com.fab5.bankingapp.model.Account;
import com.fab5.bankingapp.model.Deposit;
import com.fab5.bankingapp.model.Withdraw;
import com.fab5.bankingapp.repository.AccountRepository;
import com.fab5.bankingapp.repository.DepositRepository;
import com.fab5.bankingapp.repository.WithdrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TransactionService {

    @Autowired
    private DepositRepository depositRepository;
    @Autowired
    private WithdrawRepository withdrawRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public void processWithdraw(Withdraw withdraw)throws InsufficientFundsException{
        Account account= withdraw.getAccount();
        if (withdraw.getAmount() > account.getBalance()){
            throw new InsufficientFundsException("Insufficient Funds in the Account");
        }
        account.setBalance(account.getBalance() - withdraw.getAmount());
        withdrawRepository.save(withdraw);
        accountRepository.save(account);
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
        account.setBalance(account.getBalance() + deposit.getAmount());
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
        account.setBalance(account.getBalance()-deposit.getAmount());
    }
}
