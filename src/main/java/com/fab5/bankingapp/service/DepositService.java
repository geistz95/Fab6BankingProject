package com.fab5.bankingapp.service;

import com.fab5.bankingapp.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositService {
    @Autowired
    private DepositRepository depositRepository;
}
