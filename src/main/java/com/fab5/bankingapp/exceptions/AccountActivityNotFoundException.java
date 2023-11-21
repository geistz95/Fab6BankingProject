package com.fab5.bankingapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AccountActivityNotFoundException extends NoSuchElementFoundException{
    public AccountActivityNotFoundException(Long accountActivityID) {
        super("Account activity with id " + accountActivityID + " not found");
    }
}
