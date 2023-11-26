package com.fab5.bankingapp.model;

import com.fab5.bankingapp.enums.Medium;
import com.fab5.bankingapp.enums.TransactionStatus;
import com.fab5.bankingapp.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Deposit implements Serializable {
    /**
     * Long id
     * String type
     * String transaction_date
     * String status
     * Long payee_id
     * String medium
     * Double amount
     * String description
     */

    @Id
    @Column(name = "deposit_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long depositId;

    @Column
    @Enumerated(EnumType.STRING)
    private TransactionType type;


    private Date transaction_date;

    @Column
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;


    @Column
    private Long payee_id;

    @Column
    @Enumerated(EnumType.STRING)
    private Medium medium;

    @Column
    private Double amount;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name="account_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Account account;

    public Deposit() {
    }

    public Deposit(Long depositId, TransactionType type, Date transaction_date, TransactionStatus status, Long payee_id, Medium medium, Double amount, String description, Account account) {
        this.depositId = depositId;
        this.type = type;
        this.transaction_date = transaction_date;
        this.status = status;
        this.payee_id = payee_id;
        this.medium = medium;
        this.amount = amount;
        this.description = description;
        this.account = account;
    }


    public Long getDepositId() {
        return depositId;
    }

    public void setDepositId(Long depositId) {
        this.depositId = depositId;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Date getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(Date transaction_date) {
        this.transaction_date = transaction_date;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public Long getPayee_id() {
        return payee_id;
    }

    public void setPayee_id(Long payee_id) {
        this.payee_id = payee_id;
    }

    public Medium getMedium() {
        return medium;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
