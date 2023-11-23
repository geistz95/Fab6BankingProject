package com.fab5.bankingapp.controller;

import com.fab5.bankingapp.exceptions.AccountNotFoundException;
import com.fab5.bankingapp.model.Bill;
import com.fab5.bankingapp.exceptions.BillNotFoundException;
import com.fab5.bankingapp.response.BillResponse;
import com.fab5.bankingapp.service.BillService;
import com.fab5.bankingapp.validation.IDValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.fab5.bankingapp.response.BillResponse.*;

import java.net.URI;
import java.util.Optional;

@RestController
public class BillController {
    @Autowired
    private BillService billService;

    @GetMapping("/bills/{billId}")
    public ResponseEntity<?> getBillsById(@PathVariable Long billId){
        return BillResponse.getBillBuilder(HttpStatus.OK,billService.getBillsById(billId));
    }

    @GetMapping("/accounts/{accountId}/bills")
    public ResponseEntity<?> getBillsForSpecificAccount(@PathVariable Long accountId){
        return new ResponseEntity<>(billService.getAllBillsFromAccountId(accountId), HttpStatus.OK);
    }
    @GetMapping("customers/{customerId}/bills")
    public ResponseEntity<?> getBillsForSpecificCustomer(@PathVariable Long customerId){
        return new ResponseEntity<>(billService.getAllBillsFromCustomerId(customerId), HttpStatus.OK);
    }
    @PostMapping("/accounts/{accountId}/bills")
    public ResponseEntity createBill(@RequestBody Bill bill){
        HttpHeaders responseHeader = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(bill.getBillId()).toUri();
        responseHeader.setLocation(newPollUri);
        billService.createBill(bill);
        return BillResponse.createdBillBuilder(HttpStatus.OK, bill);
    }
    @PutMapping("/bills/{billId}")
    public ResponseEntity<?> updateBill(@RequestBody Bill bill, @PathVariable Long billId){
        billService.updateBill(bill, billId);
        return BillResponse.putBillBuilder(HttpStatus.OK);
    }
    @DeleteMapping("/bills/{billId}")
    public ResponseEntity<?> deleteBill(@PathVariable Long billId) {
        return BillResponse.deleteBillBuilder(HttpStatus.NO_CONTENT);

    }
}
