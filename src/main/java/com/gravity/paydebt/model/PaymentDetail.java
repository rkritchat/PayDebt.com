package com.gravity.paydebt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "payment_detail")
public class PaymentDetail {
    @Id
    @Column(name="transaction_ref")
    private Long transactionRef;
    private String username;
    @Column(name="debt_ref")
    private Long debtRef;
    private String description;
    private double amount;
    @Column(name="amount_paid")
    private double amountPaid;
    private int status;
    @Column(name="payment_date", columnDefinition = "DATE")
    private Date paymentDate;

    public PaymentDetail() {
    }

    public PaymentDetail(String username, Long debtRef, String description, double amount, double amountPaid, Long transactionRef, int status, Date paymentDate) {
        this.username = username;
        this.debtRef = debtRef;
        this.description = description;
        this.amount = amount;
        this.amountPaid = amountPaid;
        this.transactionRef = transactionRef;
        this.status = status;
        this.paymentDate = paymentDate;
    }

    public String getUsername() {
        return username;
    }

    public PaymentDetail setUsername(String username) {
        this.username = username;
        return this;
    }

    public Long getDebtRef() {
        return debtRef;
    }

    public PaymentDetail setDebtRef(Long debtRef) {
        this.debtRef = debtRef;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PaymentDetail setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentDetail setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public PaymentDetail setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
        return this;
    }

    public Long getTransactionRef() {
        return transactionRef;
    }

    public PaymentDetail setTransactionRef(Long transactionRef) {
        this.transactionRef = transactionRef;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public PaymentDetail setStatus(int status) {
        this.status = status;
        return this;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public PaymentDetail setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
        return this;
    }
}
