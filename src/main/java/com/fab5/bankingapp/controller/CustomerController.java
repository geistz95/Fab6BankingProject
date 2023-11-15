package com.fab5.bankingapp.controller;

import com.fab5.bankingapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;
}
