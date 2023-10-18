package com.modela.shipping.adm.controller;

import com.modela.shipping.adm.dto.AdmTokenDto;
import com.modela.shipping.adm.dto.UserCredentialDto;
import com.modela.shipping.adm.service.LoginService;
import com.modela.shipping.adm.util.exception.ShippingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<AdmTokenDto> getToken(
            @RequestBody UserCredentialDto credentialDto,
            @RequestHeader(name = "user-agent", defaultValue = "Java/17") String userAgent
    ) throws IOException, ShippingException {
        var token = this.loginService.doLogin(credentialDto, userAgent);
        return new ResponseEntity<>(new AdmTokenDto(token), HttpStatus.OK);
    }

}
