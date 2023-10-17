package com.modela.shipping.adm.repository;

import com.modela.shipping.adm.model.AdmPermission;
import com.modela.shipping.adm.model.AdmRole;
import com.modela.shipping.adm.model.AdmRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmRolePermissionRepository extends JpaRepository<AdmRolePermission, Long>{

    boolean existsByRoleAndPermission(AdmRole role, AdmPermission permission);

}
