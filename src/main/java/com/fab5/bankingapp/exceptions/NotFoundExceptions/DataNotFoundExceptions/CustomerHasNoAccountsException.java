package com.fab5.bankingapp.exceptions.NotFoundExceptions.DataNotFoundExceptions;

import com.fab5.bankingapp.exceptions.NotFoundExceptions.DataNotFoundException;
import com.fab5.bankingapp.exceptions.NotFoundExceptions.NoSuchElementFoundException;

public class CustomerHasNoAccountsException extends DataNotFoundException {
    public CustomerHasNoAccountsException(String message, Long customerID) {
        super("Error fetching customers accounts");
        throwLogError(customerID);
    }
}
