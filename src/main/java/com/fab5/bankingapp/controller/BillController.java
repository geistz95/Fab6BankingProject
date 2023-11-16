package com.fab5.bankingapp.controller;

import com.fab5.bankingapp.exceptions.BillNotFoundException;
import com.fab5.bankingapp.service.BillService;
import com.fab5.bankingapp.validation.IDValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BillController implements IDValidation<BillNotFoundException> {
    @Autowired
    private BillService billService;

    @Override
    public void verifyID(Long id) throws BillNotFoundException {

    }
}
