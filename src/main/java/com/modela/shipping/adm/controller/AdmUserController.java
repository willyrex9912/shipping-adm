package com.modela.shipping.adm.controller;

import com.modela.shipping.adm.model.AdmUser;
import com.modela.shipping.adm.service.AdmUserService;
import com.modela.shipping.adm.util.exception.ShippingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class AdmUserController {

    private final AdmUserService service;

    @PostMapping
    public ResponseEntity<AdmUser> create(@RequestBody AdmUser user) throws ShippingException {
        return new ResponseEntity<>(service.create(user), HttpStatus.CREATED);
    }
}
