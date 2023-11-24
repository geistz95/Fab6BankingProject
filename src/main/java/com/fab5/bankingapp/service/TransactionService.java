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
    @Transactional
    public void changeWithdrawal(Withdraw withdrawal, Withdraw oldWithdrawal){
        Account account = withdrawal.getAccount();
        account.setBalance(account.getBalance()+withdrawal.getAmount()-oldWithdrawal.getAmount());
        accountRepository.save(account);
    }

    @Transactional
    public void deleteWithdrawal(Long id) {
        Withdraw withdrawal = withdrawRepository.findById(id).get();
        Account account = withdrawal.getAccount();
        account.setBalance(account.getBalance()+withdrawal.getAmount());
    }

    @Transactional
    public void processDeposit(Deposit deposit){
        Account account= deposit.getAccount();
        account.setBalance(account.getBalance() + deposit.getAmount());
        depositRepository.save(deposit);
        accountRepository.save(account);
    }
}
