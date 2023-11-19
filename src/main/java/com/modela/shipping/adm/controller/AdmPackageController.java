package com.modela.shipping.adm.controller;

import com.modela.shipping.adm.dto.AdmPackageDto;
import com.modela.shipping.adm.dto.ShippingPage;
import com.modela.shipping.adm.model.AdmPackage;
import com.modela.shipping.adm.service.AdmPackageService;
import com.modela.shipping.adm.util.exception.ShippingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("packages")
@RequiredArgsConstructor
public class AdmPackageController {

    private final AdmPackageService service;

    @PostMapping
    public ResponseEntity<AdmPackage> save(@RequestBody AdmPackage entity) throws ShippingException {
        var created = this.service.save(entity);
        if (created == null)
            throw new ShippingException("Package not created").withStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.created(null).body(created);
    }

    @GetMapping
    public ResponseEntity<ShippingPage<List<AdmPackageDto>, Long>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

}
