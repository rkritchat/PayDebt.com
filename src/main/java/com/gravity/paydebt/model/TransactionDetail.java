package com.gravity.paydebt.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TransactionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private int status;
    @Column(name = "transaction_date",columnDefinition = "DATETIME")
    private Date transactionDate;

    public TransactionDetail() {
    }

    public TransactionDetail(String description, int status, Date transactionDate) {
        this.description = description;
        this.status = status;
        this.transactionDate = transactionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
