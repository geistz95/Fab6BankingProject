package com.fab5.bankingapp.controller;

import com.fab5.bankingapp.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DepositController {
    @Autowired
    private DepositService depositService;

}
