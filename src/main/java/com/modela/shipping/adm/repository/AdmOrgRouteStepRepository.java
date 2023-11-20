package com.modela.shipping.adm.repository;

import com.modela.shipping.adm.model.AdmOrgRouteStep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdmOrgRouteStepRepository extends JpaRepository<AdmOrgRouteStep, Long> {

    List<AdmOrgRouteStep> findByTargetOrganizationId(Long targetOrgId);

    List<AdmOrgRouteStep> findBySourceOrganizationId(Long source);
}
