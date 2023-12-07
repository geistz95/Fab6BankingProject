package com.fab5.bankingapp.exceptions.NotFoundExceptions.DataNotFoundExceptions;

import com.fab5.bankingapp.exceptions.NotFoundExceptions.DataNotFoundException;
import com.fab5.bankingapp.exceptions.NotFoundExceptions.NoSuchElementFoundException;

public class AccountHasNoWithdrawalsException extends DataNotFoundException {
    public AccountHasNoWithdrawalsException(Long accountID) {
        super("Account with ID " + accountID + " has no withdrawals.");
        throwLogError(accountID);
    }
}
