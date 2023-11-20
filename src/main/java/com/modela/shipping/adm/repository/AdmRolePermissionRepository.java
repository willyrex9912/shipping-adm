package com.modela.shipping.adm.repository;

import com.modela.shipping.adm.dto.RolRouteDto;
import com.modela.shipping.adm.model.AdmPermission;
import com.modela.shipping.adm.model.AdmRole;
import com.modela.shipping.adm.model.AdmRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdmRolePermissionRepository extends JpaRepository<AdmRolePermission, Long>{

    boolean existsByRoleAndPermission(AdmRole role, AdmPermission permission);
    @Query(name = "find_routes_by_rol_id", nativeQuery = true)
    List<RolRouteDto> findRoutesByRolId(@Param("rolIds") List<Long> rolId);

    @Query("""
                SELECT rp FROM AdmUserRole AS ur
                JOIN AdmRolePermission AS rp ON ur.role.roleId = rp.role.roleId
                JOIN AdmPermission AS p ON rp.permission.permissionId = p.permissionId
                WHERE ur.user.userId = :userId AND p.parentPermission.permissionId IS NOT NULL
             """)
    List<AdmRolePermission> findMyRolePermissions(@Param("userId") Long userId);
}
