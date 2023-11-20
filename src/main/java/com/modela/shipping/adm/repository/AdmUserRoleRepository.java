package com.modela.shipping.adm.repository;

import com.modela.shipping.adm.dto.RolUserDto;
import com.modela.shipping.adm.model.AdmRole;
import com.modela.shipping.adm.model.AdmUser;
import com.modela.shipping.adm.model.AdmUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AdmUserRoleRepository extends JpaRepository<AdmUserRole, Long> {
    List<AdmUserRole> findAllByUser(AdmUser user);
    Optional<AdmUserRole> findByUserAndRole(AdmUser user, AdmRole role);
    @Query(name = "find_roles_by_user_id", nativeQuery = true)
    List<RolUserDto> findRolesByUserId(@Param("userId") Long userId);
}
