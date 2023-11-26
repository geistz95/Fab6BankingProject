package com.fab5.bankingapp.model;

import com.fab5.bankingapp.enums.TransactionType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "account_activity")
public class AccountActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activityId;

    private Long accountId;
    private Double amount;
    private String timestamp;

    private TransactionType type;

    public AccountActivity(){

    }

    public AccountActivity(Long activityId, Long accountId, Double amount, String timestamp, TransactionType type) {
        this.activityId = activityId;
        this.accountId = accountId;
        this.amount = amount;
        this.timestamp = timestamp;
        this.type = type;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}
