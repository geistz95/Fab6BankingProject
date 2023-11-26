package com.fab5.bankingapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends NoSuchElementFoundException{

    public CustomerNotFoundException(Long customerId) {
        super("Customer with ID " + customerId + " not found");
        throwLogError(customerId);
    }

}
