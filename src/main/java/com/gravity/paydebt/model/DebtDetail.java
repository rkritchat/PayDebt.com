package com.gravity.paydebt.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="debt_detail")
public class DebtDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false)
    @Length(max = 5)
    private int id;

    @Column(name="debtor_id")
    @Length(max = 15)
    private String debtorId;

    @Column(name="creditor_id")
    @Length(max = 15)
    private String creditorId;

    private double amount;
    private String detail;

    @Column(name="create_date")
    private Date createDate;

    @Length(min = 1, max = 1)
    private int status;

    public DebtDetail() {
    }

    public DebtDetail(String debtorId, String creditorId, double amount, String detail, Date createDate, @Length(min = 1, max = 1) int status) {
        this.debtorId = debtorId;
        this.creditorId = creditorId;
        this.amount = amount;
        this.detail = detail;
        this.createDate = createDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDebtorId() {
        return debtorId;
    }

    public void setDebtorId(String debtorId) {
        this.debtorId = debtorId;
    }

    public String getCreditorId() {
        return creditorId;
    }

    public void setCreditorId(String creditorId) {
        this.creditorId = creditorId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}