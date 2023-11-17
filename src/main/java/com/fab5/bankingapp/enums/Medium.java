package com.fab5.bankingapp.enums;

import javax.persistence.Entity;


public enum Medium {
    //Specifies how the transaction is being handled. Balance or Rewards
    BALANCE("BALANCE"),REWARDS("REWARDS");

    private final String medium;

    Medium(String medium) {
        this.medium = medium;
    }

    public String getMedium() {
        return medium;
    }

}
