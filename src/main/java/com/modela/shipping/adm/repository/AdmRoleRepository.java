package com.modela.shipping.adm.repository;

import com.modela.shipping.adm.model.AdmRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmRoleRepository extends JpaRepository<AdmRole, Long> {

    Page<AdmRole> findAllByOrganizationIdAndSubOrganizationId(Long orgId, Long subOrgId, Pageable pageable);
}
