package com.gravity.paydebt.resource;

import com.gravity.paydebt.model.DebtDetail;
import com.gravity.paydebt.model.PaymentDetail;
import com.gravity.paydebt.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentResource {

    private PaymentService paymentService;

    @Autowired
    public PaymentResource(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/pay")
    public ResponseEntity<String> pay(@RequestBody PaymentDetail paymentDetail) {
        System.out.println("Come here ");
        return paymentService.pay(paymentDetail);
    }

    @PostMapping("/detail")
    public ResponseEntity<List<PaymentDetail>> getPaymentDetail(@RequestBody DebtDetail debtDetail) {
        return paymentService.getPaymentDetail(debtDetail);
    }
}
