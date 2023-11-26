package com.fab5.bankingapp.exceptions;

import com.fab5.bankingapp.utility.ExceptionTypeExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidDepositAmount extends RuntimeException implements ExceptionTypeExtractor {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public InvalidDepositAmount(String mustBeAPositiveNumber) {
        super(mustBeAPositiveNumber);
        logger.error("{} thrown: Invalid deposit amount, must be a positive value", getClass().getSimpleName());
    }
}
