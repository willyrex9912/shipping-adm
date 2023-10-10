package com.modela.shipping.adm.service;

import com.modela.shipping.adm.model.AdmUser;
import com.modela.shipping.adm.repository.AdmUserRepository;
import com.modela.shipping.adm.util.exception.ShippingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdmUserService {

    private final AdmUserRepository repository;
    private final PasswordEncoder encoder;

    public AdmUser create(AdmUser user) throws ShippingException {
        var userOpt = repository.findByEmail(user.getEmail());
        if (userOpt.isPresent()) {
            log.warn("user with email: {} already exists", user.getEmail());
            throw new ShippingException("email_already_exists");
        }

        // hash password here and then save
        user.setPassword(encoder.encode(user.getPassword()));
        return repository.save(user);
    }
}
