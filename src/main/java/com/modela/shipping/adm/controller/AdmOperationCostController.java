package com.modela.shipping.adm.controller;

import com.modela.shipping.adm.dto.AdmOperationCostDto;
import com.modela.shipping.adm.dto.ShippingPage;
import com.modela.shipping.adm.model.AdmOperationCost;
import com.modela.shipping.adm.service.AdmOperationCostService;
import com.modela.shipping.adm.util.exception.ShippingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("operation-costs")
@RequiredArgsConstructor
public class AdmOperationCostController {

    private final AdmOperationCostService service;

    @PostMapping
    public ResponseEntity<AdmOperationCost> save(@RequestBody AdmOperationCost entity) throws ShippingException {
        var created = this.service.save(entity);
        if (created == null)
            throw new ShippingException("Package not created").withStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.created(null).body(created);
    }

    @GetMapping
    public ResponseEntity<ShippingPage<List<AdmOperationCostDto>, Long>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

}
