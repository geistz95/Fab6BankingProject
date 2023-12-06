package com.fab5.bankingapp.service;

import com.fab5.bankingapp.enums.TransactionStatus;
import com.fab5.bankingapp.enums.TransactionType;
import com.fab5.bankingapp.exceptions.NotFoundExceptions.ModelNotFoundExceptions.AccountNotFoundException;
import com.fab5.bankingapp.exceptions.NotFoundExceptions.ModelNotFoundExceptions.WithdrawNotFoundException;
import com.fab5.bankingapp.model.Account;
import com.fab5.bankingapp.model.Withdraw;
import com.fab5.bankingapp.repository.AccountRepository;
import com.fab5.bankingapp.repository.WithdrawRepository;
import com.fab5.bankingapp.validation.IDValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        verifyID2(withdrawRepository.findById(id).get().getAccount().getId());
        Withdraw existingWithdrawal = withdrawRepository.findById(id).get();
        existingWithdrawal.setAmount(withdraw.getAmount());
        existingWithdrawal.setDescription(withdraw.getDescription());
        existingWithdrawal.setMedium(withdraw.getMedium());
        existingWithdrawal.setStatus(TransactionStatus.PENDING);
        existingWithdrawal.setTransaction_date(new Date());
        transactionService.changeWithdrawal(withdraw, existingWithdrawal);
        withdrawRepository.save(withdraw);
    }

    public void createWithdraw(Long accountId, Withdraw withdraw){
        verifyID2(accountId);
        Optional<Account> a = accountRepository.findById(accountId);
        withdraw.setPayerId(a.get().getId());
        withdraw.setAccount(a.get());
        withdraw.setStatus(TransactionStatus.PENDING);
        withdraw.setTransaction_date(new Date());
        withdraw.setType(TransactionType.WITHDRAW);
        withdrawRepository.save(withdraw);
        transactionService.processWithdraw(withdraw);
    }

    public void deleteWithdrawById(Long id){
        verifyID1(id);
        verifyID2(withdrawRepository.findById(id).get().getAccount().getId());
        transactionService.deleteWithdrawal(id);
        withdrawRepository.deleteById(id);
    }

}
