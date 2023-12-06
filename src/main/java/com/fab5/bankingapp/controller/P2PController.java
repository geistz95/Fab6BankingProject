package com.fab5.bankingapp.controller;

import com.fab5.bankingapp.model.P2PTransfer;
import com.fab5.bankingapp.repository.P2PTransferRepository;
import com.fab5.bankingapp.service.P2PTransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(P2PController.class);
    @PostMapping("/accounts/transfers/{payer_id}/{payee_id}")
    public ResponseEntity<?> createTransfer(@PathVariable Long payer_id, @PathVariable Long payee_id, @RequestBody P2PTransfer transfer){
        HttpHeaders responseHeader = new HttpHeaders();
        //This next line builds the URI link from the deposit
        logger.info("Creating new P2P URI");
        URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(transfer.getTransfer_id()).toUri();
        responseHeader.setLocation(newPollUri);
        logger.info("Attempting to add P2P transaction");
        transferService.createTransfer(payer_id,payee_id, transfer);
        return createP2PBuilder(HttpStatus.CREATED, transfer);
    }

    @GetMapping("/transfers/{transfer_id}")
    public ResponseEntity<?> getTransfer(@PathVariable Long transfer_id){
        logger.info("Attempting to get P2P Transaction of id : "+ transfer_id);
        return getP2PBuilder(HttpStatus.OK, transferService.getTransfer(transfer_id));
    }


    /**
     * This does not delete it, however it will revert the p2p process and cancel deposit and withdraw
     * @param transfer_id
     * @return
     */
    @DeleteMapping("/transfers/{transfer_id}")
    public ResponseEntity<?> deleteTransfer(@PathVariable Long transfer_id){
        logger.info("Attempting to cancel/delete P2P transaction id of {}", transfer_id);
        transferService.deleteTransfer(transfer_id);
        return deleteP2PBuilder(HttpStatus.NO_CONTENT);
    }
}
