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
    private String activityType;
    private Double amount;
    private LocalDateTime timestamp;

    private TransactionType type;

    public AccountActivity(){

    }

    public AccountActivity(Long activityId, Long accountId, String activityType, Double amount, LocalDateTime timestamp, TransactionType type) {
        this.activityId = activityId;
        this.accountId = accountId;
        this.activityType = activityType;
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

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AccountActivity{" +
                "activityId=" + activityId +
                ", accountId=" + accountId +
                ", activityType='" + activityType + '\'' +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                ", transactionType=" + type +
                '}';
    }
}
