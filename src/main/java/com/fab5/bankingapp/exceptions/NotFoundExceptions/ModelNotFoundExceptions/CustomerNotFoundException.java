package com.fab5.bankingapp.exceptions.NotFoundExceptions.ModelNotFoundExceptions;

import com.fab5.bankingapp.exceptions.NotFoundExceptions.NoSuchElementFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends NoSuchElementFoundException {

    public CustomerNotFoundException(String message, Long customerId) {
        super(message);
        throwLogError(customerId);
    }

}
