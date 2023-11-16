package com.fab5.bankingapp.enums;

import javax.persistence.Entity;

@Entity
public enum Medium {
    //Specifies how the transaction is being handled. Balance or Rewards
    BALANCE("balance"),REWARDS("rewards");

    private final String medium;

    Medium(String medium) {
        this.medium = medium;
    }

    public String getMedium() {
        return medium;
    }

}
