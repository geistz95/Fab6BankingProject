package com.fab5.bankingapp.validation;

import com.fab5.bankingapp.exceptions.NotFoundExceptions.NoSuchElementFoundException;

public interface IDValidation<T extends NoSuchElementFoundException, K extends NoSuchElementFoundException> {
    void verifyID1(String message, Long id) throws T;
    void verifyID2(String message, Long id) throws K;
}

