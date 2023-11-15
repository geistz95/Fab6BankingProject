package com.fab5.bankingapp.controller;

import com.fab5.bankingapp.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BillController {
    @Autowired
    private BillService billService;
}
