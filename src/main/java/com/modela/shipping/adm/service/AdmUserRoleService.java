package com.modela.shipping.adm.service;

import com.modela.shipping.adm.dto.RolUserDto;
import com.modela.shipping.adm.model.AdmRole;
import com.modela.shipping.adm.model.AdmUser;
import com.modela.shipping.adm.model.AdmUserRole;
import com.modela.shipping.adm.repository.AdmUserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdmUserRoleService {

    private final AdmUserRoleRepository repository;

    public void save(AdmUserRole userRole) {
        repository.save(userRole);
    }

    public void saveAll(Long userId, List<RolUserDto> roles) {

        var userRoles = roles.stream().map(rol -> {
            var oUserRol = repository.findByUserAndRole(
                    AdmUser.builder().userId(userId).build(),
                    AdmRole.builder().roleId(rol.getRolId()).build()
            );

            return oUserRol.orElseGet(() -> AdmUserRole.builder()
                    .user(AdmUser.builder().userId(userId).build())
                    .role(AdmRole.builder().roleId(rol.getRolId()).build())
                    .entryDate(LocalDateTime.now())
                    .build());
        }).toList();

        repository.saveAll(userRoles);
    }

    public List<AdmUserRole> findAllByUser(AdmUser user){
        return repository.findAllByUser(user);
    }

    public void deleteAll(Iterable<AdmUserRole> userRoles) {
        repository.deleteAll(userRoles);
    }
}
