package com.modela.shipping.adm.controller;

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

    @PostMapping("ajax")
    public ResponseEntity<String> getToken(
            @RequestBody UserCredentialDto credentialDto,
            @RequestHeader(name = "user-agent", defaultValue = "Java/17") String userAgent
    ){
        try {
            return new ResponseEntity<>(this.loginService.doLogin(credentialDto, userAgent), HttpStatus.OK);
        }catch (ShippingException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }catch (IOException ie){
            return new ResponseEntity<>("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
