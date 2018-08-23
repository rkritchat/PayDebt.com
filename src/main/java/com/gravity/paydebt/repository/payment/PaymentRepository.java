package com.gravity.paydebt.repository.payment;

import com.gravity.paydebt.model.PaymentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;


public interface PaymentRepository extends JpaRepository<PaymentDetail, Integer> {
    PaymentDetail findByDebtRef(Long debtRef);

    @Modifying
    @Transactional
    @Query("UPDATE PaymentDetail p SET p.status=:status WHERE p.debtRef=:debtRef AND p.transactionRef=:transactionRef")
    void updateStatus(@Param("status") int status, @Param("debtRef") Long debtRef, @Param("transactionRef") Long transactionRef);
}
