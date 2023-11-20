package com.fab5.bankingapp.service;

import com.fab5.bankingapp.exceptions.WithdrawNotFoundException;
import com.fab5.bankingapp.model.Withdraw;
import com.fab5.bankingapp.repository.WithdrawRepository;
import com.fab5.bankingapp.validation.IDValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class WithdrawService implements IDValidation<WithdrawNotFoundException> {
    @Autowired
    private WithdrawRepository withdrawRepository;

    @Override
    public void verifyID(Long id) throws WithdrawNotFoundException {
        Optional<Withdraw> checkWithdraw = withdrawRepository.findById(id);
        if(checkWithdraw.isEmpty()) {
            throw new WithdrawNotFoundException(id);
        }
    }

    public Iterable<Withdraw> getAllWithdrawalsByAccount(Long id){
        return withdrawRepository.findByAccount(id);
    }

    public Optional<Withdraw> getWithdrawById(Long id){
        verifyID(id);
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
