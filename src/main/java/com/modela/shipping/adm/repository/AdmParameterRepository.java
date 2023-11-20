package com.modela.shipping.adm.repository;

import com.modela.shipping.adm.model.AdmOrganization;
import com.modela.shipping.adm.model.AdmParameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmParameterRepository extends JpaRepository<AdmParameter, Long> {
    Page<AdmParameter> findAllByOrganization(Pageable pageable, AdmOrganization organization);
}
