package com.modela.shipping.adm.controller;

import com.modela.shipping.adm.model.AdmOrgRoute;
import com.modela.shipping.adm.service.AdmOrgRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("routes")
@RequiredArgsConstructor
public class AdmOrgRouteController {

    private final AdmOrgRouteService routeService;

    @GetMapping("/{orgRouteId}")
    public ResponseEntity<AdmOrgRoute> findById(@PathVariable("orgRouteId") Long orgRouteId) {
        return routeService.findById(orgRouteId)
                .map(route -> new ResponseEntity<>(route, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
