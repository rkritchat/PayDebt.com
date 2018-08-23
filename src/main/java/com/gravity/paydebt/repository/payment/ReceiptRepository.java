package com.gravity.paydebt.repository.payment;

import com.gravity.paydebt.model.ReceiptDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReceiptRepository extends JpaRepository<ReceiptDetail,Integer> {
    @Query("SELECT COALESCE(SUM(r.amout),0) FROM ReceiptDetail r WHERE r.debtReference =:debtReference")
    double getTotalAmountPaid(@Param("debtReference") long debtReference);
}
