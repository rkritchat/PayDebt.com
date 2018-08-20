package com.gravity.paydebt.repository.debt;

import com.gravity.paydebt.model.DebtDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DebtRepository extends JpaRepository<DebtDetail,Integer> {
}
