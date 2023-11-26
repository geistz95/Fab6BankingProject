package com.fab5.bankingapp.validation;

import com.fab5.bankingapp.exceptions.InvalidDepositAmount;
import com.fab5.bankingapp.service.DepositService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface DepositValidation {
    default void validateAmount(Double amount) throws InvalidDepositAmount {
        if (amount <= 0) {
            Logger logger = LoggerFactory.getLogger(DepositService.class);
            logger.error("The amount is not a positive number");
            throw new InvalidDepositAmount("Deposit amount must be a positive number");
        }
    }
}
