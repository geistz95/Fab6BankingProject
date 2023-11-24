package com.fab5.bankingapp.service;

import com.fab5.bankingapp.exceptions.AccountNotFoundException;
import com.fab5.bankingapp.exceptions.CustomerNotFoundException;
import com.fab5.bankingapp.exceptions.WithdrawNotFoundException;
import com.fab5.bankingapp.model.Account;
import com.fab5.bankingapp.model.Customer;
import com.fab5.bankingapp.model.Withdraw;
import com.fab5.bankingapp.repository.AccountRepository;
import com.fab5.bankingapp.repository.WithdrawRepository;
import com.fab5.bankingapp.validation.IDValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class WithdrawService implements IDValidation<WithdrawNotFoundException, AccountNotFoundException> {
    @Autowired
    private WithdrawRepository withdrawRepository;
  
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionService transactionService;

    @Override
    public void verifyID1(Long id) throws WithdrawNotFoundException {
        Optional<Withdraw> checkWithdraw = withdrawRepository.findById(id);
        if(checkWithdraw.isEmpty()){
            throw new WithdrawNotFoundException(id);
        }
    }

    @Override
    public void verifyID2(Long id) throws AccountNotFoundException {
        Optional<Account> checkAccount = accountRepository.findById(id);
        if(checkAccount.isEmpty()){
            throw new AccountNotFoundException(id);
        }
    }

    public Iterable<Withdraw> getAllWithdrawalsByAccount(Long id){
        verifyID2(id);
        return withdrawRepository.findByAccount(id);
    }

    public Optional<Withdraw> getWithdrawById(Long id){
        verifyID1(id);
        return withdrawRepository.findById(id);
    }

    public void updateWithdraw(Long id, Withdraw withdraw){
        verifyID1(id);
        Withdraw existingWithdrawal = withdrawRepository.findById(id).get();
        existingWithdrawal.setAmount(withdraw.getAmount());
        existingWithdrawal.setDescription(withdraw.getDescription());
        existingWithdrawal.setMedium(withdraw.getMedium());
        existingWithdrawal.setStatus(withdraw.getStatus());
        existingWithdrawal.setTransaction_date(withdraw.getTransaction_date());
        withdrawRepository.save(withdraw);
    }

    public void createWithdraw(Long accountId, Withdraw withdraw){
        verifyID2(accountId);
        Optional<Account> a = accountRepository.findById(accountId);
        withdraw.setAccount(a.get());
        withdrawRepository.save(withdraw);
        transactionService.processWithdraw(withdraw);
    }

    public void deleteWithdrawById(Long id){
        verifyID1(id);
        withdrawRepository.deleteById(id);
    }

}
