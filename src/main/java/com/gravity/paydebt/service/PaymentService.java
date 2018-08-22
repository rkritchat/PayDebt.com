package com.gravity.paydebt.service;

import com.gravity.paydebt.model.PaymentDetail;
import com.gravity.paydebt.model.TransactionDetail;
import com.gravity.paydebt.repository.payment.PaymentRepository;
import com.gravity.paydebt.repository.payment.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PaymentService {

    private TransactionRepository transactionRepository;
    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(TransactionRepository transactionRepository, PaymentRepository paymentRepository) {
        this.transactionRepository = transactionRepository;
        this.paymentRepository = paymentRepository;
    }

    public ResponseEntity<String> pay(PaymentDetail paymentDetail) {
        transactionRepository.save(new TransactionDetail(null, 0, new Date()));
        Long transactionId = transactionRepository.getMaxId();
        paymentDetail.setTransactionRef(transactionId).setPaymentDate(new Date());
        paymentRepository.save(paymentDetail);

        return new ResponseEntity<>("Payment Done.", HttpStatus.OK);
    }

}
