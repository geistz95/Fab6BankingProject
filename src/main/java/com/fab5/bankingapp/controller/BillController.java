package com.fab5.bankingapp.controller;

import com.fab5.bankingapp.model.Bill;
import com.fab5.bankingapp.exceptions.BillNotFoundException;
import com.fab5.bankingapp.service.BillService;
import com.fab5.bankingapp.validation.IDValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Controller
public class BillController implements IDValidation<BillNotFoundException> {
    @Autowired
    private BillService billService;

    @Override
    public void verifyID(Long id) throws BillNotFoundException {
        Optional<Bill> checkBill = billService.getBillsById(id);
        if(checkBill.isEmpty()) {
            throw new BillNotFoundException(id);
        }
    }
    @GetMapping("/bills/{billId}")
    public ResponseEntity<?> getBillsById(@PathVariable Long id){
        return new ResponseEntity<>(billService.getBillsById(id), HttpStatus.OK);
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
        return new ResponseEntity(bill,responseHeader,HttpStatus.CREATED);
    }
    @PutMapping("/bills/{billId}")
    public ResponseEntity<?> updateBill(@RequestBody Bill bill, @PathVariable Long id){
        billService.updateBill(bill, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/bills/{billId}")
    public ResponseEntity<?> deleteBill(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
