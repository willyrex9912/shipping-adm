package com.modela.shipping.adm.service;

import com.modela.shipping.adm.dto.AdmPermissionDto;
import com.modela.shipping.adm.dto.ShippingPage;
import com.modela.shipping.adm.repository.AdmPermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdmPermissionService {

    private final AdmPermissionRepository repository;

    public ShippingPage<List<AdmPermissionDto>, Long> findAll(Pageable pageable) {
        var permissions = repository.findByParentPermissionNotNull(pageable);
        var permissionsDto = permissions
                .stream()
                .map(AdmPermissionDto::new)
                .toList();

        return ShippingPage.of(permissionsDto, permissions.getTotalElements());
    }
}
