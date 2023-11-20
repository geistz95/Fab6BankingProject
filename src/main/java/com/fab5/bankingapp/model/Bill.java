package com.fab5.bankingapp.model;

import com.fab5.bankingapp.enums.TransactionStatus;

import javax.persistence.*;

@Entity
public class Bill {
    /**
     * Long billid
     * String status
     * String payee
     * String nickname
     * String creation_date
     * String payment_date
     * String reccuring_date
     * String upcoming_payment_date
     * Double payment_amount
     * String account_id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BILL_ID")
    private Long billId;

    @Column
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @Column
    private String payee;

    @Column
    private String nickName;

    @Column
    private String creation_date;

    @Column
    private String payment_date;

    @Column
    private String reccuring_date;

    @Column
    private String upcoming_payment_date;

    @Column
    private Double payment_amount;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Bill (){

    }

    public Bill(Long billId, TransactionStatus status, String payee, String nickName, String creation_date, String payment_date, String reccuring_date, String upcoming_payment_date, Double payment_amount, Account account) {
        this.billId = billId;
        this.status = status;
        this.payee = payee;
        this.nickName = nickName;
        this.creation_date = creation_date;
        this.payment_date = payment_date;
        this.reccuring_date = reccuring_date;
        this.upcoming_payment_date = upcoming_payment_date;
        this.payment_amount = payment_amount;
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public String getReccuring_date() {
        return reccuring_date;
    }

    public void setReccuring_date(String reccuring_date) {
        this.reccuring_date = reccuring_date;
    }

    public String getUpcoming_payment_date() {
        return upcoming_payment_date;
    }

    public void setUpcoming_payment_date(String upcoming_payment_date) {
        this.upcoming_payment_date = upcoming_payment_date;
    }

    public Double getPayment_amount() {
        return payment_amount;
    }

    public void setPayment_amount(Double payment_amount) {
        this.payment_amount = payment_amount;
    }

}
