package com.fab5.bankingapp.exceptions;


public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(String message){
        super(message);
    }
}
