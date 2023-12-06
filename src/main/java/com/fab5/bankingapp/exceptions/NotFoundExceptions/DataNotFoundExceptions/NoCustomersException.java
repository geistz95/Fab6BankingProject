package com.fab5.bankingapp.exceptions.NotFoundExceptions.DataNotFoundExceptions;

import com.fab5.bankingapp.exceptions.NotFoundExceptions.DataNotFoundException;

public class NoCustomersException extends DataNotFoundException {
    public NoCustomersException(String message) {
        super(message);
        throwLogError();
    }
}
