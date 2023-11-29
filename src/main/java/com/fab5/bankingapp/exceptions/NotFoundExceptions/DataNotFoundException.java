package com.fab5.bankingapp.exceptions.NotFoundExceptions;

import com.fab5.bankingapp.utility.ExceptionTypeExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DataNotFoundException extends RuntimeException implements ExceptionTypeExtractor {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    public DataNotFoundException(String message) {
        super(message);
    }

    protected void throwLogError(Long ID) {
        logger.error("{} thrown: {} with ID {} not found", getClass().getSimpleName(), extractExceptionType(), ID);
    }
}
