package com.modela.shipping.adm.controller;

import com.modela.shipping.adm.service.AdmOrgRouteService;
import com.modela.shipping.adm.util.exception.ShippingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("routes")
@RequiredArgsConstructor
public class AdmOrgRouteController {

    private final AdmOrgRouteService routeService;

    @GetMapping
    public ResponseEntity<?> findRoutes(
            @RequestParam(name = "source") Long source,
            @RequestParam(name = "target") Long target
    ) throws ShippingException {
        routeService.findRoute(source, target);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
