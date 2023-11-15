package com.fab5.bankingapp.service;

import com.fab5.bankingapp.repository.WithdrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WithdrawService {
    @Autowired
    private WithdrawRepository withdrawRepository;
}
