package com.fab5.bankingapp.exceptions.NotFoundExceptions.ModelNotFoundExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BillNotFoundException extends NoSuchElementFoundException {
    public BillNotFoundException(Long billID) {
        super("Bill with id " + billID + " not found");
        throwLogError(billID);
    }
}
