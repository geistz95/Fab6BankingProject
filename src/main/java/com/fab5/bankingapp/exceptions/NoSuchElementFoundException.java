package com.fab5.bankingapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public abstract class NoSuchElementFoundException extends RuntimeException {
    public NoSuchElementFoundException(String message) {
        super(message);
    }

}
