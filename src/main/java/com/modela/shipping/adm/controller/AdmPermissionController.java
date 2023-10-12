package com.modela.shipping.adm.controller;

import com.modela.shipping.adm.dto.AdmPermissionDto;
import com.modela.shipping.adm.service.AdmPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("permissions")
@RequiredArgsConstructor
public class AdmPermissionController {

    private final AdmPermissionService service;

    @GetMapping
    public ResponseEntity<List<AdmPermissionDto>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
}
