package com.modela.shipping.adm.controller;

import com.modela.shipping.adm.dto.AdmPermissionDto;
import com.modela.shipping.adm.dto.ShippingPage;
import com.modela.shipping.adm.service.AdmPermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("permissions")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "X-Total-Count"})
public class AdmPermissionController {

    private final AdmPermissionService service;

    @GetMapping
    public ShippingPage<List<AdmPermissionDto>, Long> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }
}
