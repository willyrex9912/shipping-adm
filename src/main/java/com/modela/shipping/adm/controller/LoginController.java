package com.modela.shipping.adm.controller;

import com.modela.shipping.adm.model.AdmUser;
import com.modela.shipping.adm.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class LoginController {

    private final TokenService tokenService;

    @PostMapping("ajax")
    public String getToken(@RequestBody AdmUser user) throws IOException {
        return this.tokenService.generateToken(
                user.getEmail(),
                user.getOrganizationId(),
                user.getUserId(),
                new ArrayList<>()
        );
    }

}
