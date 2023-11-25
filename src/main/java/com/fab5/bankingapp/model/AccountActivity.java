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
    private Double amount;
    private LocalDateTime timestamp;

    private Deposit deposit;

    private Withdraw withdraw;

    private  P2PTransfer p2PTransfer;

    public AccountActivity(){

    }

    public AccountActivity(Long activityId, Long accountId, String activityType, Double amount, LocalDateTime timestamp, Deposit deposit, Withdraw withdraw, P2PTransfer p2PTransfer) {
        this.activityId = activityId;
        this.accountId = accountId;
        this.activityType = activityType;
        this.amount = amount;
        this.timestamp = timestamp;
        this.deposit = deposit;
        this.withdraw = withdraw;
        this.p2PTransfer = p2PTransfer;
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

    public Deposit getDeposit() {
        return deposit;
    }

    public void setDeposit(Deposit deposit) {
        this.deposit = deposit;
    }

    public Withdraw getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(Withdraw withdraw) {
        this.withdraw = withdraw;
    }

    public P2PTransfer getP2PTransfer() {
        return p2PTransfer;
    }

    public void setP2PTransfer(P2PTransfer p2PTransfer) {
        this.p2PTransfer = p2PTransfer;
    }

    @Override
    public String toString() {
        return "AccountActivity{" +
                "activityId=" + activityId +
                ", accountId=" + accountId +
                ", activityType='" + activityType + '\'' +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                ", deposit=" + deposit +
                ", withdraw=" + withdraw +
                ", p2PTransfer=" + p2PTransfer +
                '}';
    }
}
