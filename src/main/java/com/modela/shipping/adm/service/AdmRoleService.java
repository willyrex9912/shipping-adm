package com.modela.shipping.adm.service;

import com.modela.shipping.adm.dto.AdmRoleDto;
import com.modela.shipping.adm.dto.ShippingPage;
import com.modela.shipping.adm.model.AdmRole;
import com.modela.shipping.adm.repository.AdmRoleRepository;
import com.modela.shipping.adm.util.exception.ShippingException;
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
    private final ShippingSecurityContext securityContext;

    public ShippingPage<List<AdmRoleDto>, Long> findAll(Pageable pageable) {
        var roles = repository.findAllByOrganizationIdAndSubOrganizationId(securityContext.getOrgId(), securityContext.getSubOrgId(), pageable);
        var rolesDto = roles.stream()
                .map(AdmRoleDto::new)
                .toList();

        return ShippingPage.of(rolesDto, roles.getTotalElements());
    }

    public Optional<AdmRole> findById(Long id) {
        return repository.findById(id);
    }

    public AdmRole save(AdmRole role) {
        // set orgId and subOrgId
        role.setOrganizationId(securityContext.getOrgId());
        role.setSubOrganizationId(securityContext.getSubOrgId());
        return repository.save(role);
    }

    public AdmRole update(Long roleId, AdmRole role) throws ShippingException {
        var originalRole = this.findById(roleId);
        if (originalRole.isEmpty()) return null;
        if (originalRole.get().getRoleId().equals(role.getRoleId())) throw new ShippingException("invalid_update");

        return this.save(role);
    }

    public void delete(AdmRole role) {
        repository.delete(role);
    }
}
