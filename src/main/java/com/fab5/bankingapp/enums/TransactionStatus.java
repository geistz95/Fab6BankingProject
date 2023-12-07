package com.fab5.bankingapp.enums;

import javax.persistence.Entity;


public enum TransactionStatus {
    //This specifies the status of the transaction, Pending, Cancelled, Completed, (Bill will only use this) Recurring

    PENDING("PENDING"),CANCELLED("CANCELLED"),COMPLETED("COMPLETED");

    private final String status;

    TransactionStatus(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
