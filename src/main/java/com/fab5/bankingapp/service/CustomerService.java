package com.fab5.bankingapp.service;

import com.fab5.bankingapp.model.Customer;
import com.fab5.bankingapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
}


