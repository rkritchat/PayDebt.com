package com.gravity.paydebt.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="debt_detail")
public class DebtDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Long getId() {
        return id;
    }

    public DebtDetail setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDebtorId() {
        return debtorId;
    }

    public DebtDetail setDebtorId(String debtorId) {
        this.debtorId = debtorId;
        return this;
    }

    public String getCreditorId() {
        return creditorId;
    }

    public DebtDetail setCreditorId(String creditorId) {
        this.creditorId = creditorId;
        return this;
    }

    public double getAmount() {
        return amount;
    }

    public DebtDetail setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public String getDetail() {
        return detail;
    }

    public DebtDetail setDetail(String detail) {
        this.detail = detail;
        return this;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public DebtDetail setCreateDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public DebtDetail setStatus(int status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "DebtDetail{" +
                "id=" + id +
                ", debtorId='" + debtorId + '\'' +
                ", creditorId='" + creditorId + '\'' +
                ", amount=" + amount +
                ", detail='" + detail + '\'' +
                ", createDate=" + createDate +
                ", status=" + status +
                '}';
    }
}