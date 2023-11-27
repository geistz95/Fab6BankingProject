package com.fab5.bankingapp.controller;

import com.fab5.bankingapp.model.Bill;
import com.fab5.bankingapp.response.BillResponse;
import com.fab5.bankingapp.service.BillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class BillController {
    @Autowired
    private BillService billService;

    private static final Logger logger = LoggerFactory.getLogger(BillController.class);

    @GetMapping("/bills/{billId}")
    public ResponseEntity<?> getBillsById(@PathVariable Long billId){
        logger.info("Getting all bills from bill id  :" + billId);
        return BillResponse.getBillBuilder(HttpStatus.OK,billService.getBillsById(billId));
    }

    @GetMapping("/accounts/{accountId}/bills")
    public ResponseEntity<?> getBillsForSpecificAccount(@PathVariable Long accountId){
        logger.info("Getting all bills from account id  :" + accountId);
        return new ResponseEntity<>(billService.getAllBillsFromAccountId(accountId), HttpStatus.OK);
    }
    @GetMapping("customers/{customerId}/bills")
    public ResponseEntity<?> getBillsForSpecificCustomer(@PathVariable Long customerId){
        logger.info("Getting all bills from customer id  :" + customerId);
        return new ResponseEntity<>(billService.getAllBillsFromCustomerId(customerId), HttpStatus.OK);
    }
    @PostMapping("/accounts/{accountId}/bills")
    public ResponseEntity createBill(@PathVariable Long accountId, @RequestBody Bill bill){
        HttpHeaders responseHeader = new HttpHeaders();
        logger.info("Creating new Bill URI");
        URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(bill.getBillId()).toUri();
        logger.info("Post Request received : creating bill");
        responseHeader.setLocation(newPollUri);
        billService.createBill(accountId, bill);
        logger.info("Returning successful post request");
        return BillResponse.createdBillBuilder(HttpStatus.CREATED, bill);
    }
    @PutMapping("/bills/{billId}")
    public ResponseEntity<?> updateBill(@RequestBody Bill bill, @PathVariable Long billId){
        logger.info("Editting bill ID : "+billId);
        billService.updateBill(bill, billId);
        logger.info("Successfully updated bill ID : "+billId);
        return BillResponse.putBillBuilder(HttpStatus.OK);
    }
    @DeleteMapping("/bills/{billId}")
    public ResponseEntity<?> deleteBill(@PathVariable Long billId) {
        logger.info("Delete request received ID : "+billId);
        billService.deleteBillById(billId);
        logger.info("Deleted deposit ID  : "+billId);
        return BillResponse.deleteBillBuilder(HttpStatus.NO_CONTENT);

    }
}
