package com.fab5.bankingapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class WithdrawNotFoundException extends NoSuchElementFoundException{
    public WithdrawNotFoundException(Long withdrawId) {
        super("Withdraw with id " + withdrawId + " not found");
    }
}
