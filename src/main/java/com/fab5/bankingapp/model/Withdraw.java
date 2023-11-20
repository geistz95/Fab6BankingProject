package com.fab5.bankingapp.model;

import com.fab5.bankingapp.enums.Medium;
import com.fab5.bankingapp.enums.TransactionStatus;
import com.fab5.bankingapp.enums.TransactionType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Withdraw {
  
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;


    @Column
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column
    private String transaction_date;

    @Column
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_ID",nullable = false)
    private Long payerId;

    @Column
    @Enumerated(EnumType.STRING)
    private Medium medium;

    @Column
    private Double amount;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;

    public Withdraw() {
    }

    public Withdraw(Long id, TransactionType type, String transaction_date, TransactionStatus status, Long payerId, Medium medium, Double amount, String description, Account account) {
        this.id = id;
        this.type = type;
        this.transaction_date = transaction_date;
        this.status = status;
        this.payerId = payerId;
        this.medium = medium;
        this.amount = amount;
        this.description = description;
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }


    public Long getPayerId() {
        return payerId;
    }

    public void setPayerId(Long payeeId) {
        this.payerId = payeeId;
    }

    public Medium getMedium() {
        return medium;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




}

    /**
     * Long id
     * String type enum
     * String transaction_date
     * String status enum
     * Long payee_id
     * String medium enum
     * Double amount
     * String description
     */




