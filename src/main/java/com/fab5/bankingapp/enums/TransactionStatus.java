package com.fab5.bankingapp.enums;

import javax.persistence.Entity;

@Entity
public enum TransactionStatus {
    //This specifies the status of the transaction, Pending, Cancelled, Completed, (Bill will only use this) Recurring

    PENDING("pending"),CANCELLED("cancelled"),COMPLETED("completed");

    private final String status;

    TransactionStatus(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
