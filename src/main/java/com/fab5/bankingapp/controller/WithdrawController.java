package com.fab5.bankingapp.controller;

import com.fab5.bankingapp.exceptions.WithdrawNotFoundException;
import com.fab5.bankingapp.model.Withdraw;
import com.fab5.bankingapp.service.WithdrawService;
import com.fab5.bankingapp.validation.IDValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class WithdrawController implements IDValidation<WithdrawNotFoundException> {
    @Autowired
    private WithdrawService withdrawService;

    @Override
    public void verifyID(Long id) throws WithdrawNotFoundException {

    }
}
