package com.gravity.paydebt.repository.payment;

import com.gravity.paydebt.model.PaymentDetail;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentRepository extends JpaRepository<PaymentDetail, Integer> {
}
