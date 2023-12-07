package com.fab5.bankingapp.model;

import com.fab5.bankingapp.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "account_activity")
public class AccountActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnoreProperties
    private Long activityId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private  Account account;


//    private List<Withdraw> withdraws;
//
//    private  List<Deposit> deposits;

    private Double amount;


    private Date timestamp;

    private TransactionType type;



    public AccountActivity(Account account, Long accountId, Double amount, Date timestamp, TransactionType type) {
        this.activityId = activityId;
        //this.accountId = accountId;
        this.amount = amount;
        this.timestamp = timestamp;
        this.type = type;
        this.account = account;
    }

    public AccountActivity(){

    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

//    public Long getAccountId() {
//        return accountId;
//    }
//
//    public void setAccountId(Long accountId) {
//        this.accountId = accountId;
//    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}
