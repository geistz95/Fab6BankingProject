package com.fab5.bankingapp.model;

import com.fab5.bankingapp.enums.TransactionType;

import javax.persistence.*;

@Entity
public class Withdraw {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String type = TransactionType.WITHDRAW.getString();

    @Column
    private String transaction_date;


    @Column
    private String status;

    @Column
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="accountID",nullable = false)
    private Long payeeId;

    @Column
    private Double amount;

    @Column
    private String description;

    public Withdraw() {
    }

    public Withdraw(Long id, String type, String transaction_date, String status, Long payeeId, Double amount, String description) {
        this.id = id;
        this.type = type;
        this.transaction_date = transaction_date;
        this.status = status;
        this.payeeId = payeeId;
        this.amount = amount;
        this.description = description;
    }

    public String getType() {
        return type;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(Long payeeId) {
        this.payeeId = payeeId;
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

    @Override
    public String toString() {
        return "Withdraw{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", transaction_date='" + transaction_date + '\'' +
                ", status='" + status + '\'' +
                ", payeeId=" + payeeId +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
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
}
