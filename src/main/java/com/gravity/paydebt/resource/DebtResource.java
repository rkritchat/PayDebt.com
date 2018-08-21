package com.gravity.paydebt.resource;

import com.gravity.paydebt.model.DebtDetail;
import com.gravity.paydebt.service.DebtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/debt")
public class DebtResource {

    @Autowired
    private DebtService debtService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody DebtDetail debtDetail) {
        return debtService.createDebt(debtDetail);
    }

    @PostMapping("/detail")
    public ResponseEntity<List<DebtDetail>> getDebtDetail(@RequestBody DebtDetail debtDetail) {
        return debtService.getDebtDetail(debtDetail);
    }

}
