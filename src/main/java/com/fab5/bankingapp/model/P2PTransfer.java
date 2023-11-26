package com.fab5.bankingapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity
@Table(name = "p2p_transfer")
public class P2PTransfer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long transfer_id;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Account receiver;

    @ManyToOne
    @JoinColumn(name = "giver_id")
    private Account giver;

    @Column
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "deposit_id")
    @JsonIgnore
    private Deposit deposit;

    @ManyToOne
    @JoinColumn(name = "withdraw_id")
    @JsonIgnore
    private Withdraw withdraw;

    public P2PTransfer(Long transfer_id, Account receiver, Account giver, Double amount) {
        this.transfer_id = transfer_id;
        this.receiver = receiver;
        this.giver = giver;
        this.amount = amount;
    }

    public P2PTransfer() {
    }

    public Long getTransfer_id() {
        return transfer_id;
    }

    public void setTransfer_id(Long transfer_id) {
        this.transfer_id = transfer_id;
    }

    public Account getReceiver() {
        return receiver;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }

    public Account getGiver() {
        return giver;
    }

    public void setGiver(Account giver) {
        this.giver = giver;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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
}
