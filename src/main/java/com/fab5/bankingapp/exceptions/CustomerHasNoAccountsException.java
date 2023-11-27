package com.fab5.bankingapp.exceptions;

public class CustomerHasNoAccountsException extends NoSuchElementFoundException{
    public CustomerHasNoAccountsException(Long customerID) {
        super("Customer with ID " + customerID + " has no accounts.");
        throwLogError(customerID);
    }
}
