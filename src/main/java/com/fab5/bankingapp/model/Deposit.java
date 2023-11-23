package com.fab5.bankingapp.model;

import com.fab5.bankingapp.enums.Medium;
import com.fab5.bankingapp.enums.TransactionStatus;
import com.fab5.bankingapp.enums.TransactionType;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

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
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long depositId;

    @Column
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column
    private String transaction_date;

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
    private Account account;

    public Deposit() {
    }

    public Deposit(Long depositId, TransactionType type, String transcation_date, TransactionStatus status, Long payee_id, Medium medium, Double amount, String description, Account account) {
        this.depositId = depositId;
        this.type = type;
        this.transaction_date = transcation_date;
        this.status = status;
        this.payee_id = payee_id;
        this.medium = medium;
        this.amount = amount;
        this.description = description;
        this.account=account;
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

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transcation_date) {
        this.transaction_date = transcation_date;
    }


    public Long getPayee_id() {
        return payee_id;
    }

    public void setPayee_id(Long payee_id) {
        this.payee_id = payee_id;
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

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
