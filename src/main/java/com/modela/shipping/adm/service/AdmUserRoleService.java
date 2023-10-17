package com.modela.shipping.adm.service;

import com.modela.shipping.adm.model.AdmUserRole;
import com.modela.shipping.adm.repository.AdmUserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdmUserRoleService {

    private final AdmUserRoleRepository repository;

    public void save(AdmUserRole userRole) {
        repository.save(userRole);
    }

    public void saveAll(List<AdmUserRole> userRoles) {
        var filteredUserRoles = userRoles.stream()
                .filter(ur -> !repository.existsByUserAndRole(ur.getUser(), ur.getRole()))
                .toList();
        repository.saveAll(filteredUserRoles);
    }

    public void deleteAll(Iterable<AdmUserRole> userRoles) {
        repository.deleteAll(userRoles);
    }
}
