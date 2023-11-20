package com.fab5.bankingapp.service;

import com.fab5.bankingapp.model.Withdraw;
import com.fab5.bankingapp.repository.AccountRepository;
import com.fab5.bankingapp.repository.WithdrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WithdrawService {
    @Autowired
    private WithdrawRepository withdrawRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Iterable<Withdraw> getAllWithdrawalsByAccount(Long id){
        return withdrawRepository.findByAccount(id);
    }

    public Optional<Withdraw> getWithdrawById(Long id){
        return withdrawRepository.findById(id);
    }

    public void updateWithdraw(Long id, Withdraw withdraw){
        Withdraw existingWithdrawal = withdrawRepository.findById(id).get();
        existingWithdrawal.setAmount(withdraw.getAmount());
        existingWithdrawal.setDescription(withdraw.getDescription());
        existingWithdrawal.setMedium(withdraw.getMedium());
        existingWithdrawal.setStatus(withdraw.getStatus());
        existingWithdrawal.setTransaction_date(withdraw.getTransaction_date());
        withdrawRepository.save(withdraw);
    }

    public Withdraw createWithdraw(Withdraw withdraw){
        return withdrawRepository.save(withdraw);
    }

    public void deleteWithdrawById(Long id){
        withdrawRepository.deleteById(id);
    }
}
