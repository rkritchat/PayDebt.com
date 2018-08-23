package com.gravity.paydebt.repository.debt;

import com.gravity.paydebt.model.DebtDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DebtRepository extends JpaRepository<DebtDetail,Integer> {
    List<DebtDetail> findByDebtorReference(String debtorId);

    DebtDetail findById(Long id);
}
