package com.modela.shipping.adm.controller;

import com.modela.shipping.adm.dto.ShippingPage;
import com.modela.shipping.adm.model.AdmVehicle;
import com.modela.shipping.adm.service.AdmiVehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class AdmVehicleController {

    private final AdmiVehicleService service;

    @GetMapping
    public ResponseEntity<ShippingPage<List<AdmVehicle>, Long>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdmVehicle> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<AdmVehicle> save(@RequestBody AdmVehicle vehicle) {
        return ResponseEntity.ok(service.save(vehicle));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdmVehicle> update(@PathVariable Long id, @RequestBody AdmVehicle vehicle) {
        return ResponseEntity.ok(service.update(id, vehicle));
    }
}
