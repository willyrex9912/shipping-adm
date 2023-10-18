package com.modela.shipping.adm.service;

import com.modela.shipping.adm.dto.ShippingPage;
import com.modela.shipping.adm.model.AdmUser;
import com.modela.shipping.adm.repository.AdmUserRepository;
import com.modela.shipping.adm.repository.AdmUserRoleRepository;
import com.modela.shipping.adm.util.exception.ShippingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdmUserService {

    private final AdmUserRepository repository;
    private final AdmUserRoleService userRoleService;
    private final PasswordEncoder encoder;

    public ShippingPage<List<AdmUser>, Long> findAll(Pageable pageable){
        var page = repository.findAll(pageable);
        return ShippingPage.of(page.toList(), page.getTotalElements());
    }

    public Optional<AdmUser> findById(Long id) {
        return repository.findById(id);
    }

    public AdmUser createWithRoles(AdmUser user) throws ShippingException {
        var userOpt = repository.findByEmail(user.getEmail());
        if (Objects.isNull(user.getUserId()) && userOpt.isPresent()) {
            log.warn("user with email: {} already exists", user.getEmail());
            throw new ShippingException("User email already exists").withStatus(HttpStatus.CONFLICT);
        }

        user.setPassword(encoder.encode(user.getPassword()));
        userRoleService.saveAll(user.getUserRoles());
        return repository.save(user);
    }

    public Boolean checkPassword(String password, String encodedPassword){
        return encoder.matches(password, encodedPassword);
    }

    public Optional<AdmUser> findByEmail(String email){
        return this.repository.findByEmail(email);
    }
}
