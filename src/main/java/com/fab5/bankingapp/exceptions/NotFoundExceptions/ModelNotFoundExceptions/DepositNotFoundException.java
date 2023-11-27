package com.fab5.bankingapp.exceptions.NotFoundExceptions.ModelNotFoundExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DepositNotFoundException extends NoSuchElementFoundException {

    public DepositNotFoundException(Long depositId) {
        super("Deposit with id " + depositId + " not found");
        throwLogError(depositId);
    }


}
