package com.fab5.bankingapp.exceptions;

public class InvalidDepositAmount extends RuntimeException {
    public InvalidDepositAmount(String mustBeAPositiveNumber) {
    }
}
