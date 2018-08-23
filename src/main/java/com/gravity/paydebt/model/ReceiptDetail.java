package com.gravity.paydebt.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "receipt_detail")
public class ReceiptDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "debt_reference")
    private Long debtReference;
    @Column(name = "debtor_reference")
    private String debtorReference;
    private double amout;
    @Column(name = "receipt_date", columnDefinition = "DATETIME")
    private Date receiptDate;

    public ReceiptDetail() {
    }

    public ReceiptDetail(Long debtReference, String debtorReference, double amout, Date receiptDate) {
        this.debtReference = debtReference;
        this.debtorReference = debtorReference;
        this.amout = amout;
        this.receiptDate = receiptDate;
    }

    public Long getId() {
        return id;
    }

    public ReceiptDetail setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getDebtReference() {
        return debtReference;
    }

    public ReceiptDetail setDebtReference(Long debtReference) {
        this.debtReference = debtReference;
        return this;
    }

    public String getDebtorReference() {
        return debtorReference;
    }

    public ReceiptDetail setDebtorReference(String debtorReference) {
        this.debtorReference = debtorReference;
        return this;
    }

    public double getAmout() {
        return amout;
    }

    public ReceiptDetail setAmout(double amout) {
        this.amout = amout;
        return this;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public ReceiptDetail setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
        return this;
    }
}
