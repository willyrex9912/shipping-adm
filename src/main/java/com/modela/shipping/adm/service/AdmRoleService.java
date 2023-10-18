package com.modela.shipping.adm.service;

import com.modela.shipping.adm.dto.ShippingPage;
import com.modela.shipping.adm.model.AdmRole;
import com.modela.shipping.adm.repository.AdmRolePermissionRepository;
import com.modela.shipping.adm.repository.AdmRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdmRoleService {

    private final AdmRoleRepository repository;
    private final AdmRolePermissionService rolePermissionService;

    public ShippingPage<List<AdmRole>, Long> findAll(Pageable pageable) {
        var roles = repository.findAll(pageable);
        return ShippingPage.of(roles.toList(), roles.getTotalElements());
    }

    public Optional<AdmRole> findbyId(Long id) {
        return repository.findById(id);
    }

    public AdmRole save(AdmRole role) {
        rolePermissionService.saveAll(role.getRolePermissions());
        return repository.save(role);
    }

    public void delete(AdmRole role) {
        rolePermissionService.deleteAll(role.getRolePermissions());
        repository.delete(role);
    }

}
