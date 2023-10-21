package com.modela.shipping.adm.service;

import com.modela.shipping.adm.dto.ShippingPage;
import com.modela.shipping.adm.model.AdmUser;
import com.modela.shipping.adm.model.AdmUserDetails;
import com.modela.shipping.adm.repository.AdmUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
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

    public ShippingPage<List<AdmUser>, Long> findAll(Pageable pageable){
        var page = repository.findAll(pageable);
        return ShippingPage.of(page.toList(), page.getTotalElements());
    }

    public Optional<AdmUser> findById(Long id) {
        return repository.findById(id);
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
