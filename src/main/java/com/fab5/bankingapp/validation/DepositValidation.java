package com.fab5.bankingapp.validation;

import com.fab5.bankingapp.exceptions.InvalidDepositAmount;

public interface DepositValidation {
    default void validateAmount(Double amount) throws InvalidDepositAmount {
        if (amount <= 0) {
            throw new InvalidDepositAmount("Deposit amount must be a positive number");
        }
    }
}
