package com.fab5.bankingapp.exceptions.NotFoundExceptions.DataNotFoundExceptions;

import com.fab5.bankingapp.exceptions.NotFoundExceptions.NoSuchElementFoundException;

public class CustomerHasNoAccountsException extends NoSuchElementFoundException {
    public CustomerHasNoAccountsException(Long customerID) {
        super("Customer with ID " + customerID + " has no accounts.");
        throwLogError(customerID);
    }
}
