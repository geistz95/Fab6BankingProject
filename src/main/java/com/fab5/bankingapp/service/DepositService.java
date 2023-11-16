package com.fab5.bankingapp.service;

import com.fab5.bankingapp.exceptions.DepositNotFoundException;
import com.fab5.bankingapp.model.Deposit;
import com.fab5.bankingapp.repository.CustomerRepository;
import com.fab5.bankingapp.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepositService {

    @Autowired
    private DepositRepository depositRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public Optional<Deposit> getDepositByID(Long id){
        return depositRepository.findById(id);
    }

    public void createDeposit(Deposit deposit){
        depositRepository.save(deposit);
    }

    public void editDeposit(Deposit deposit, Long id){
        Deposit oldDeposit = depositRepository.findById(id).get();
        oldDeposit=deposit;
    }

    public void deleteDeposit(Deposit deposit){
        depositRepository.delete(deposit);
    }

    public void deleteDepositByID(Long id){
        depositRepository.deleteById(id);
    }

}
