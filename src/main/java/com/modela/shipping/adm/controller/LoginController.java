package com.modela.shipping.adm.controller;

import com.modela.shipping.adm.dto.AdmTokenDto;
import com.modela.shipping.adm.dto.UserCredentialDto;
import com.modela.shipping.adm.service.AuthService;
import com.modela.shipping.adm.util.exception.ShippingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author willyrex
 */

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class LoginController {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<AdmTokenDto> getToken(@RequestBody UserCredentialDto credentialDto)
            throws IOException, ShippingException {
        return ResponseEntity.ok(authService.signin(credentialDto));
    }

}
