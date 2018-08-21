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

    @Column(name="debtor_ref")
    @Length(max = 15)
    private String debtorReference;

    @Column(name="creditor_ref")
    @Length(max = 15)
    private String creditorReference;

    private double amount;
    private String description;

    @Column(name="create_date",columnDefinition = "DATETIME")
    private Date createDate;

    private int status;

    public DebtDetail() {
    }

    public DebtDetail(String debtorReference, String creditorReference, double amount, String description, Date createDate, @Length(min = 1, max = 1) int status) {
        this.debtorReference = debtorReference;
        this.creditorReference = creditorReference;
        this.amount = amount;
        this.description = description;
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

    public String getDebtorReference() {
        return debtorReference;
    }

    public DebtDetail setDebtorReference(String debtorReference) {
        this.debtorReference = debtorReference;
        return this;
    }

    public String getCreditorReference() {
        return creditorReference;
    }

    public DebtDetail setCreditorReference(String creditorReference) {
        this.creditorReference = creditorReference;
        return this;
    }

    public double getAmount() {
        return amount;
    }

    public DebtDetail setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DebtDetail setDescription(String description) {
        this.description = description;
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
                ", debtorId='" + debtorReference + '\'' +
                ", creditorId='" + creditorReference + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                ", status=" + status +
                '}';
    }
}