package com.fab5.bankingapp.model;

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
    private BigDecimal amount;
    private LocalDateTime timestamp;

    public AccountActivity(){

    }

    public AccountActivity(Long activityId, Long accountId, String activityType, BigDecimal amount, LocalDateTime timestamp) {
        this.activityId = activityId;
        this.accountId = accountId;
        this.activityType = activityType;
        this.amount = amount;
        this.timestamp = timestamp;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "AccountActivity{" +
                "activityId=" + activityId +
                ", accountId=" + accountId +
                ", activityType='" + activityType + '\'' +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                '}';
    }
}
