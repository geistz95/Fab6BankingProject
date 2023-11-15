package com.fab5.bankingapp.model;

import com.fab5.bankingapp.enums.TransactionType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Deposit {
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
    private String type = TransactionType.DEPOSIT.getString();

    @Column
    private String transcation_date;

    @Column
    private String status;

    @Column
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="accountID",nullable = false)
    private Long payee_id;

    @Column
    private String medium;

    @Column
    private Double amount;

    @Column
    private String description;



    public Long getDepositId() {
        return depositId;
    }

    public void setDepositId(Long depositId) {
        this.depositId = depositId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTranscation_date() {
        return transcation_date;
    }

    public void setTranscation_date(String transcation_date) {
        this.transcation_date = transcation_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getPayee_id() {
        return payee_id;
    }

    public void setPayee_id(Long payee_id) {
        this.payee_id = payee_id;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
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
