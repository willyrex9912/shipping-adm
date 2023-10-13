package com.modela.shipping.adm.service;

import com.modela.shipping.adm.dto.UserCredentialDto;
import com.modela.shipping.adm.model.AdmUser;
import com.modela.shipping.adm.util.exception.ShippingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

/**
 * Service to authentication.
 * @author willyrex
 * @version 1.0
 */

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AdmUserService userService;
    private final AdmTokenCredentialService tokenCredentialService;

    public String doLogin(UserCredentialDto credentialDto, String userAgent) throws ShippingException, IOException {
        Optional<AdmUser> user = this.userService.findByEmail(credentialDto.getUsername());
        if (user.isEmpty()) throw new ShippingException("Wrong credentials");
        boolean matchPassword = this.userService.checkPassword(credentialDto.getPassword(), user.get().getPassword());
        if(matchPassword){
            return this.tokenCredentialService.doLogin(user.get(), userAgent);
        }
        throw new ShippingException("Wrong credentials");
    }

}
