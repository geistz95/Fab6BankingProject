package com.fab5.bankingapp.enums;

import javax.persistence.Entity;


public enum TransactionType {
    DEPOSIT("DEPOSIT"), WITHDRAW("WITHDRAW"),P2P("P2P");
    //Specifies type of transaction P2P, Deposit, Withdraw

    private final String string;
    TransactionType(String string) {
        this.string=string;
    }

    public String getString() {
        return string;
    }
}
