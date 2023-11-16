package com.fab5.bankingapp.validation;

import com.fab5.bankingapp.exceptions.NoSuchElementFoundException;

public interface IDValidation<T extends NoSuchElementFoundException> {
    void verifyID(Long id) throws T;
}
