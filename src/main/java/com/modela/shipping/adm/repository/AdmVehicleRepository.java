package com.modela.shipping.adm.repository;

import com.modela.shipping.adm.model.AdmOrganization;
import com.modela.shipping.adm.model.AdmVehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmVehicleRepository extends JpaRepository<AdmVehicle, Long> {
    Page<AdmVehicle> findByOrganizationOrOrganizationIsNull(Pageable pageable, AdmOrganization organization);
}
