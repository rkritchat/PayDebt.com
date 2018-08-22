package com.gravity.paydebt.repository.payment;

import com.gravity.paydebt.model.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<TransactionDetail,Integer> {


    @Query("SELECT MAX(t.id) FROM TransactionDetail t")
    long getMaxId();

}
