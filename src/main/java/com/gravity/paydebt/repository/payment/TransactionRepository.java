package com.gravity.paydebt.repository.payment;

import com.gravity.paydebt.model.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface TransactionRepository extends JpaRepository<TransactionDetail,Integer> {


    @Query("SELECT MAX(t.id) FROM TransactionDetail t")
    long getMaxId();

    @Modifying
    @Transactional
    @Query("UPDATE TransactionDetail t SET t.description=:description, t.status=:status WHERE t.id=:id")
    void updateStatus(@Param("description") String description, @Param("status") int status, @Param("id") Long id);
}
