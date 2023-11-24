package com.fab5.bankingapp.controller;

import com.fab5.bankingapp.model.P2PTransfer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class P2PController {

    @PostMapping("/accounts/{account_id}/transfers")
    public ResponseEntity<?> createTransfer(@PathVariable Long account_id ,@RequestBody P2PTransfer transfer){

    }

    @GetMapping("/transfers/{transfer_id}")
    public ResponseEntity<?> getTransfer(@PathVariable Long transfer_id){

    }

    @PutMapping("/transfers/{transfer_id}")
    public ResponseEntity<?> editTransfer(@PathVariable Long transfer_id, @RequestBody P2PTransfer transfer){

    }

    @DeleteMapping("/transfers/{transfer_id")
    public ResponseEntity<?> deleteTransfer(@PathVariable Long transfer_id){

    }
}
