package com.modela.shipping.adm.controller;

import com.modela.shipping.adm.dto.ShippingPage;
import com.modela.shipping.adm.model.AdmOrganization;
import com.modela.shipping.adm.service.AdmOrganizationService;
import com.modela.shipping.adm.util.exception.ShippingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("organizations")
@RequiredArgsConstructor
public class AdmOrganizationController {

    private final AdmOrganizationService service;

    @GetMapping
    public ResponseEntity<ShippingPage<List<AdmOrganization>, Long>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdmOrganization> findById(@PathVariable("id") Long id) throws ShippingException {
        var oOrganization = service.findById(id);

        if (oOrganization.isEmpty())
            throw new ShippingException("Organization not found").withStatus(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(oOrganization.get());
    }

    @PostMapping
    public ResponseEntity<AdmOrganization> save(@RequestBody AdmOrganization organization) throws ShippingException {
        var created = service.save(organization);

        if (created == null)
            throw new ShippingException("Organization not created").withStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        return ResponseEntity.created(null).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdmOrganization> update(@PathVariable("id") Long id, @RequestBody AdmOrganization organization) throws ShippingException {
        var oOrganization = service.findById(id);

        if (oOrganization.isEmpty())
            throw new ShippingException("Organization not found").withStatus(HttpStatus.NOT_FOUND);

        organization.setOrganizationId(id);
        var updated = service.save(organization);

        if (updated == null)
            throw new ShippingException("Organization not updated").withStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        return ResponseEntity.ok(updated);
    }

    @GetMapping("/organizationsByParent/{organizationId}")
    public ResponseEntity<ShippingPage<List<AdmOrganization>, Long>> findAllSubOrganizations(Pageable pageable, @PathVariable Long organizationId) {
        return ResponseEntity.ok(service.findAllOrgsByParentId(pageable, organizationId));
    }

}
