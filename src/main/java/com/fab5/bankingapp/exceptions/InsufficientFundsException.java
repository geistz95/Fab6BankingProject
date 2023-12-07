package com.fab5.bankingapp.exceptions;


import com.fab5.bankingapp.utility.ExceptionTypeExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InsufficientFundsException extends RuntimeException implements ExceptionTypeExtractor {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public InsufficientFundsException(String message){
        super(message);
        logger.error("{} thrown: Insufficient amount of funds", getClass().getSimpleName());
    }
}
