package com.modela.shipping.adm.service;

import com.modela.shipping.adm.model.AdmRolePermission;
import com.modela.shipping.adm.repository.AdmRolePermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdmRolePermissionService {

    private final AdmRolePermissionRepository repository;

    public void save(AdmRolePermission rolePermission) {
        repository.save(rolePermission);
    }

    public void saveAll(List<AdmRolePermission> rolePermissions) {
        // TODO: Validar si el rolePermission ya existe, actualizar solamente cuado trae Id
        repository.saveAll(rolePermissions);
    }

    public void deleteAll(Iterable<AdmRolePermission> rolePermissions) {
        repository.deleteAll(rolePermissions);
    }
}
