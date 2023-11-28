package com.fab5.bankingapp.exceptions.NotFoundExceptions.ModelNotFoundExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends NoSuchElementFoundException {
    public AccountNotFoundException(Long accountId) {
        super("Account with id " + accountId + " not found");
        throwLogError(accountId);
    }
}
