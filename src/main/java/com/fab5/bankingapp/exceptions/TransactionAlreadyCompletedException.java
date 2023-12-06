package com.fab5.bankingapp.exceptions;

import com.fab5.bankingapp.enums.TransactionStatus;

public class TransactionAlreadyCompletedException extends RuntimeException {
    public TransactionAlreadyCompletedException(){
        super("Transaction status is already completed");
    }
}
