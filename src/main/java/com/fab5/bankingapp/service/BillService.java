package com.fab5.bankingapp.service;

import com.fab5.bankingapp.repository.AccountRepository;
import com.fab5.bankingapp.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;
}
