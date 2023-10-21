package com.modela.shipping.adm.service;

import com.modela.shipping.adm.dto.ShippingPage;
import com.modela.shipping.adm.model.AdmRole;
import com.modela.shipping.adm.repository.AdmRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdmRoleService {

    private final AdmRoleRepository repository;

    public ShippingPage<List<AdmRole>, Long> findAll(Pageable pageable) {
        var roles = repository.findAll(pageable);
        return ShippingPage.of(roles.toList(), roles.getTotalElements());
    }

    public Optional<AdmRole> findById(Long id) {
        return repository.findById(id);
    }

    public AdmRole save(AdmRole role) {
        // TODO: set org and subOrg
        role.setOrganization(2500L);
        role.setSubOrganizationId(25001L);
        return repository.save(role);
    }

    public void delete(AdmRole role) {
        repository.delete(role);
    }
}
