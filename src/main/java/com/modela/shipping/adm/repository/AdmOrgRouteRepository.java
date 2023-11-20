package com.modela.shipping.adm.repository;

import com.modela.shipping.adm.model.AdmOrgRoute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdmOrgRouteRepository extends JpaRepository<AdmOrgRoute, Long> {

    List<AdmOrgRoute> findByOrganizationId(Long organizationId);
}
