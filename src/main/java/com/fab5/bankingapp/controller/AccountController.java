package com.fab5.bankingapp.controller;

import com.fab5.bankingapp.exceptions.AccountNotFoundException;
import com.fab5.bankingapp.model.Account;
import com.fab5.bankingapp.service.AccountService;
import com.fab5.bankingapp.validation.IDValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class AccountController implements IDValidation<AccountNotFoundException> {
    @Autowired
    private AccountService accountService;

    @Override
    public void verifyID(Long id) throws AccountNotFoundException {

    }
}
