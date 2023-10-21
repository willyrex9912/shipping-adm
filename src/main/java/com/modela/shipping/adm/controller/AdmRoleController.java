package com.modela.shipping.adm.controller;

import com.modela.shipping.adm.dto.ShippingPage;
import com.modela.shipping.adm.model.AdmRole;
import com.modela.shipping.adm.service.AdmRoleService;
import com.modela.shipping.adm.util.exception.ShippingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("roles")
@RequiredArgsConstructor
public class AdmRoleController {

    private final AdmRoleService service;

    @GetMapping
    public ResponseEntity<ShippingPage<List<AdmRole>, Long>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdmRole> findById(@PathVariable("id") Long id) throws ShippingException {
        var oRole = service.findById(id);

        if (oRole.isEmpty())
            throw new ShippingException("Role not found").withStatus(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(oRole.get());
    }

    @PostMapping
    public ResponseEntity<AdmRole> save(@RequestBody AdmRole role) throws ShippingException {
        service.save(role);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdmRole> update(@PathVariable("id") Long id, @RequestBody AdmRole role) throws ShippingException {
        var oRole = service.findById(id);

        if (oRole.isEmpty())
            throw new ShippingException("Role not found").withStatus(HttpStatus.NOT_FOUND);

        role.setRoleId(id);
        var updated = service.save(role);

        if (updated == null)
            throw new ShippingException("Role not updated").withStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        return ResponseEntity.ok(updated);
    }
}
