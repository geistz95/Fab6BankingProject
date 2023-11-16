package com.fab5.bankingapp.enums;

import javax.persistence.Entity;


public enum TransactionType {
    DEPOSIT("deposit"), WITHDRAW("withdraw"),P2P("p2p");
    //Specifies type of transaction P2P, Deposit, Withdraw

    private final String string;
    TransactionType(String string) {
        this.string=string;
    }

    public String getString() {
        return string;
    }
}
