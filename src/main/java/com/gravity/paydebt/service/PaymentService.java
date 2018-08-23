package com.gravity.paydebt.service;

import com.gravity.paydebt.model.DebtDetail;
import com.gravity.paydebt.model.PaymentDetail;
import com.gravity.paydebt.model.ReceiptDetail;
import com.gravity.paydebt.model.TransactionDetail;
import com.gravity.paydebt.repository.debt.DebtRepository;
import com.gravity.paydebt.repository.payment.PaymentRepository;
import com.gravity.paydebt.repository.payment.ReceiptRepository;
import com.gravity.paydebt.repository.payment.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PaymentService {

    private TransactionRepository transactionRepository;
    private PaymentRepository paymentRepository;
    private DebtRepository debtRepository;
    private ReceiptRepository receiptRepository;

    @Autowired
    public PaymentService(TransactionRepository transactionRepository, PaymentRepository paymentRepository, DebtRepository debtRepository, ReceiptRepository receiptRepository) {
        this.transactionRepository = transactionRepository;
        this.paymentRepository = paymentRepository;
        this.debtRepository = debtRepository;
        this.receiptRepository = receiptRepository;
    }

    public ResponseEntity<String> pay(PaymentDetail paymentDetail) {
        long transactionId = generateTransactionReference();
        paymentDetail.setTransactionRef(transactionId).setPaymentDate(new Date());
        paymentRepository.save(paymentDetail);

        return new ResponseEntity<>("Payment Done.", HttpStatus.OK);
    }

    private Long generateTransactionReference() {
        transactionRepository.save(new TransactionDetail(null, 0, new Date()));
        return transactionRepository.getMaxId();
    }

    public ResponseEntity<List<PaymentDetail>> getPaymentDetail(DebtDetail debtDetail) {
        List<DebtDetail> debtDetails = debtRepository.findByDebtorReference(debtDetail.getDebtorReference());
        List<PaymentDetail> result = new ArrayList<>();
        debtDetails.forEach(obj->{
            PaymentDetail tmp = paymentRepository.findByDebtRef(obj.getId());
            if (tmp != null) {
                result.add(tmp);
            }
        });

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Status 1 is accept payment (Creditor confirmed the payment)
     * @param paymentDetail
     * @return
     */
    public ResponseEntity<String> confirmPayment(PaymentDetail paymentDetail) {
        transactionRepository.updateStatus(paymentDetail.getDescription(),
                paymentDetail.getStatus(),paymentDetail.getTransactionRef());
        paymentRepository.updateStatus(paymentDetail.getStatus(),
                paymentDetail.getDebtRef(), paymentDetail.getTransactionRef());

        if (paymentDetail.getStatus() == 1) {
            receiptRepository.save(new ReceiptDetail()
                    .setAmout(paymentDetail.getAmountPaid())
                    .setDebtReference(paymentDetail.getDebtRef())
                    .setReceiptDate(new Date())
                    .setDebtorReference(paymentDetail.getUsername())
            );
            DebtDetail debtDetail = debtRepository.findById(paymentDetail.getDebtRef());
            double totalAmountPaid = receiptRepository.getTotalAmountPaid(paymentDetail.getDebtRef());
            if (totalAmountPaid == debtDetail.getAmount()) {
                debtDetail.setStatus(1);
                debtRepository.save(debtDetail);
            }
        }
        return new ResponseEntity<>("Confirm payment Successfully", HttpStatus.OK);
    }

}
