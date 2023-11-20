package com.modela.shipping.adm.controller;

import com.modela.shipping.adm.dto.ShippingPage;
import com.modela.shipping.adm.model.AdmParameter;
import com.modela.shipping.adm.service.AdmParameterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parameters")
@RequiredArgsConstructor
public class AdmParameterController {

    private final AdmParameterService service;

    @GetMapping
    public ResponseEntity<ShippingPage<List<AdmParameter>, Long>> getAll(Pageable pageable) {
        return ResponseEntity.ok(service.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdmParameter> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<AdmParameter> save(@RequestBody AdmParameter parameter) {
        return ResponseEntity.ok(service.save(parameter));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdmParameter> update(@PathVariable Long id, @RequestBody AdmParameter parameter) {
        return ResponseEntity.ok(service.update(id, parameter));
    }
}
