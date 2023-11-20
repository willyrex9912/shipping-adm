package com.modela.shipping.adm.service;

import com.modela.shipping.adm.dto.AdmTokenDto;
import com.modela.shipping.adm.dto.UserCredentialDto;
import com.modela.shipping.adm.model.AdmUser;
import com.modela.shipping.adm.repository.AdmUserRepository;
import com.modela.shipping.adm.repository.AdmUserRoleRepository;
import com.modela.shipping.adm.util.exception.ShippingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AdmUserRepository admUserRepository;
    private final AdmUserRoleRepository admUserRoleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder encoder;
    private final AdmUserRoleService userRoleService;

    public AdmTokenDto signin(UserCredentialDto authRequest) throws ShippingException, IOException {
        var user = admUserRepository.findByEmail(authRequest.username());

        if (user.isEmpty())
            throw new ShippingException("invalid user request !").withStatus(HttpStatus.NOT_FOUND);

        var roles = admUserRoleRepository.findAllByUser(user.get());

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password()));
        if (authentication.isAuthenticated()) {
            var token = jwtService.generateToken(user.get(), roles);
            return new AdmTokenDto(token);
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

    @Transactional
    public AdmUser createWithRoles(AdmUser user) throws ShippingException {
        var userOpt = admUserRepository.findByEmail(user.getEmail());
        if (Objects.isNull(user.getUserId()) && userOpt.isPresent()) {
            log.warn("user with email: {} already exists", user.getEmail());
            throw new ShippingException("User email already exists").withStatus(HttpStatus.CONFLICT);
        }

        user.setPassword(encoder.encode(user.getPassword()));
        var userCreated = admUserRepository.save(user);
        userRoleService.saveAll(user.getUserId(), user.getRoles());
        return userCreated;
    }

    public Boolean checkPassword(String password, String encodedPassword){
        return encoder.matches(password, encodedPassword);
    }
}
