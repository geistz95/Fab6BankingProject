package com.fab5.bankingapp.exceptions.NotFoundExceptions.DataNotFoundExceptions;

import com.fab5.bankingapp.exceptions.NotFoundExceptions.DataNotFoundException;

public class NoAccountsException extends DataNotFoundException {
    public NoAccountsException(String message) {
        super(message);
        throwLogError();
    }
}
