package com.modela.shipping.adm.service;

import com.modela.shipping.adm.dto.AdmPermissionDto;
import com.modela.shipping.adm.dto.AdmRolePermissionDto;
import com.modela.shipping.adm.dto.ShippingPage;
import com.modela.shipping.adm.repository.AdmPermissionRepository;
import com.modela.shipping.adm.repository.AdmRolePermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdmPermissionService {

    private final AdmPermissionRepository repository;
    private final AdmRolePermissionRepository rolePermissionRepository;
    private final ShippingSecurityContext securityContext;

    public ShippingPage<List<AdmPermissionDto>, Long> findAll(Pageable pageable) {
        var permissions = repository.findByParentPermissionNotNull(pageable);
        var permissionsDto = permissions
                .stream()
                .map(AdmPermissionDto::new)
                .toList();

        return ShippingPage.of(permissionsDto, permissions.getTotalElements());
    }

    public List<AdmRolePermissionDto> myPermissions() {
        return rolePermissionRepository.findMyRolePermissions(this.securityContext.userId()).stream()
                .map(AdmRolePermissionDto::new)
                .toList();
    }
}
