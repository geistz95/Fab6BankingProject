package com.fab5.bankingapp.exceptions.NotFoundExceptions.DataNotFoundExceptions;

import com.fab5.bankingapp.exceptions.NotFoundExceptions.DataNotFoundException;

public class NoAccountActivitiesException extends DataNotFoundException {
    public NoAccountActivitiesException(String message) {
        super(message);
        throwLogError();
    }
}
