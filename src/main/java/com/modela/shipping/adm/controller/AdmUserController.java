package com.modela.shipping.adm.controller;

import com.modela.shipping.adm.dto.ShippingPage;
import com.modela.shipping.adm.model.AdmUser;
import com.modela.shipping.adm.service.AdmUserService;
import com.modela.shipping.adm.service.AuthService;
import com.modela.shipping.adm.util.exception.ShippingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class AdmUserController {

    private final AdmUserService service;
    private final AuthService authService;

    @GetMapping
    public ResponseEntity<ShippingPage<List<AdmUser>, Long>> findAll(@AuthenticationPrincipal UserDetails userDetails, Pageable pageable) {
        // Prueba para obtener datos del usuario autenticado
        System.out.println(userDetails.getUsername());
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AdmUser>> findAllWithRoles() {
        return ResponseEntity.ok(service.findAllWithRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdmUser> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<AdmUser> save(@RequestBody AdmUser user) {
        return ResponseEntity.created(null).body(authService.createWithRoles(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdmUser> update(@PathVariable("id") Long id, @RequestBody AdmUser user) throws ShippingException {
        var userDB = service.findById(id);

        user.setUserId(id);
        var updated = authService.createWithRoles(user);

        return ResponseEntity.ok(updated);
    }
}
