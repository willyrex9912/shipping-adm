package com.modela.shipping.adm.controller;

import com.modela.shipping.adm.dto.ShippingPage;
import com.modela.shipping.adm.model.AdmUser;
import com.modela.shipping.adm.service.AdmUserService;
import com.modela.shipping.adm.util.exception.ShippingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class AdmUserController {

    private final AdmUserService service;

    @GetMapping
    public ResponseEntity<ShippingPage<List<AdmUser>, Long>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdmUser> findById(@PathVariable("id") Long id) throws ShippingException {
        var oUser = service.findById(id);

        if (oUser.isEmpty())
            throw new ShippingException("User not found").withStatus(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(oUser.get());
    }

    @PostMapping
    public ResponseEntity<AdmUser> save(@RequestBody AdmUser user) throws ShippingException {
        var created = service.createWithRoles(user);

        if (created == null)
            throw new ShippingException("User not created").withStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        return ResponseEntity.created(null).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdmUser> update(@PathVariable("id") Long id, @RequestBody AdmUser user) throws ShippingException {
        var oUser = service.findById(id);

        if (oUser.isEmpty())
            throw new ShippingException("User not found").withStatus(HttpStatus.NOT_FOUND);

        user.setUserId(id);
        var updated = service.createWithRoles(user);

        if (updated == null)
            throw new ShippingException("User not updated").withStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        return ResponseEntity.ok(updated);
    }
}
