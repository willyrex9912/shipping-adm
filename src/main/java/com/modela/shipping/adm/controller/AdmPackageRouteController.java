package com.modela.shipping.adm.controller;

import com.modela.shipping.adm.service.AdmPackageRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("package-routes")
@RequiredArgsConstructor
public class AdmPackageRouteController {

    private final AdmPackageRouteService packageRouteService;

    @GetMapping
    public ResponseEntity<?> findRoutes(
            @RequestParam(name = "source") Long source,
            @RequestParam(name = "target") Long target
    ) {
        var routes = packageRouteService.findRoutes(source, target);
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }
}