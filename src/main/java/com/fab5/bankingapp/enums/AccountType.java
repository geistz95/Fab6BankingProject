package com.fab5.bankingapp.enums;


public enum AccountType {
    // This will specify what type of account it is, Checking, Savings, etc.


         Savings("SAVINGS"), Credit("CREDIT"), Cheque("CHEQUE"), JointAccount("JOINTACCOUNT");

         private final String accountType;

    AccountType(String accountType) {
        this.accountType = accountType;
    }
}
