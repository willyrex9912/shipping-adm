package com.modela.shipping.adm.repository;

import com.modela.shipping.adm.model.AdmPermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmPermissionRepository extends JpaRepository<AdmPermission, Long> {

    Page<AdmPermission> findByParentPermissionNotNull(Pageable pageable);
}
