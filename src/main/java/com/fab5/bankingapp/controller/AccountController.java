package com.fab5.bankingapp.controller;

import com.fab5.bankingapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;
}
