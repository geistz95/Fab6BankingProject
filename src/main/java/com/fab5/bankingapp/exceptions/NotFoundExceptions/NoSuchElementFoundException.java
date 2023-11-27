package com.fab5.bankingapp.exceptions.NotFoundExceptions;

import com.fab5.bankingapp.utility.NotFoundTypeExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public abstract class NoSuchElementFoundException extends RuntimeException implements NotFoundTypeExtractor {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public NoSuchElementFoundException(String message) {
        super(message);
    }

    protected void throwLogError(Long ID) {
        logger.error("{} thrown: {} with ID {} not found", getClass().getSimpleName(), getSimplifiedNameOfExceptionOfNotFound(), ID);
    }


}
