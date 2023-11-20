package com.modela.shipping.adm.repository;

import com.modela.shipping.adm.model.AdmOrganization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmOrganizationRepository extends JpaRepository<AdmOrganization, Long> {
    Page<AdmOrganization> findAllByParentOrganizationId(Long parentOrganizationId, Pageable pageable);
}
