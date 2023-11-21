package com.fab5.bankingapp.validation;

import com.fab5.bankingapp.exceptions.DepositNotFoundException;
import com.fab5.bankingapp.exceptions.NoSuchElementFoundException;

public interface IDValidation<T extends NoSuchElementFoundException, K extends NoSuchElementFoundException> {
    void verifyID(Long id) throws DepositNotFoundException;

    void verifyID1(Long id) throws T;
    void verifyID2(Long id) throws K;
}

