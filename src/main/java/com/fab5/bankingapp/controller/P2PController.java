package com.fab5.bankingapp.controller;

import com.fab5.bankingapp.model.P2PTransfer;
import com.fab5.bankingapp.repository.P2PTransferRepository;
import com.fab5.bankingapp.service.P2PTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


import static com.fab5.bankingapp.response.P2PResponse.*;

@RestController
public class P2PController {

    @Autowired
    private P2PTransferService transferService;
    @PostMapping("/accounts/{account_id}/transfers")
    public ResponseEntity<?> createTransfer(@PathVariable Long account_id ,@RequestBody P2PTransfer transfer){
        HttpHeaders responseHeader = new HttpHeaders();
        //This next line builds the URI link from the deposit
        URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(transfer.getTransfer_id()).toUri();
        responseHeader.setLocation(newPollUri);
        transferService.createTransfer(account_id,transfer);
        return createP2PBuilder(HttpStatus.CREATED, transfer);
    }

    @GetMapping("/transfers/{transfer_id}")
    public ResponseEntity<?> getTransfer(@PathVariable Long transfer_id){
        return getP2PBuilder(HttpStatus.OK, transferService.getTransfer(transfer_id));
    }


    /**
     * This does not delete it, however it will revert the p2p process and cancel deposit and withdraw
     * @param transfer_id
     * @return
     */
    @DeleteMapping("/transfers/{transfer_id}")
    public ResponseEntity<?> deleteTransfer(@PathVariable Long transfer_id){
        return deleteP2PBuilder(HttpStatus.NO_CONTENT);
    }
}
