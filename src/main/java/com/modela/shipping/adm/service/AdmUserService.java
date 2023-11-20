package com.modela.shipping.adm.service;

import com.modela.shipping.adm.dto.ShippingPage;
import com.modela.shipping.adm.model.AdmUser;
import com.modela.shipping.adm.model.AdmUserDetails;
import com.modela.shipping.adm.repository.AdmUserRepository;
import com.modela.shipping.adm.repository.AdmUserRoleRepository;
import com.modela.shipping.adm.util.exception.ShippingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdmUserService {

    private final AdmUserRepository repository;
    private final AdmUserRoleRepository admUserRoleRepository;

    public ShippingPage<List<AdmUser>, Long> findAll(Pageable pageable){
        var page = repository.findAll(pageable);
        return ShippingPage.of(page.toList(), page.getTotalElements());
    }

    public List<AdmUser> findAllWithRoles(){
        var users = repository.findAll().stream().peek(user -> {
            user.setRoles(admUserRoleRepository
                    .findRolesByUserId(user.getUserId()));
        }).toList();
        return users;
    }

    public AdmUser findById(Long id) {
        var oUser = repository.findById(id);
        if (oUser.isEmpty())
            throw new ShippingException("User not found")
                    .withStatus(HttpStatus.NOT_FOUND);

        return oUser.get();
    }

    public Optional<AdmUser> findByEmail(String email){
        return this.repository.findByEmail(email);
    }

    public UserDetailsService userDetailsService() {
        return username -> repository.findByEmail(username)
                .map(AdmUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
