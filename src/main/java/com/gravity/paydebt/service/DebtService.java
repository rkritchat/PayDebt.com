package com.gravity.paydebt.service;

import com.gravity.paydebt.model.DebtDetail;
import com.gravity.paydebt.repository.debt.DebtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.List;

@Service
public class DebtService {

    private DebtRepository debtRepository;

    public DebtService() {}

    @Autowired
    public DebtService(DebtRepository debtRepository) {
        this.debtRepository = debtRepository;
    }

    public ResponseEntity<String> createDebt(DebtDetail debtDetail) {
        debtRepository.save(debtDetail);
        return new ResponseEntity<>("Create Debt Successfully", HttpStatus.OK);
    }

    public ResponseEntity<List<DebtDetail>> getDebtDetail(DebtDetail debtDetail) {
        List<DebtDetail> result = debtRepository.findByDebtorReference(debtDetail.getDebtorReference());
        return new ResponseEntity<>(result, HttpStatus.OK);

    }
}
