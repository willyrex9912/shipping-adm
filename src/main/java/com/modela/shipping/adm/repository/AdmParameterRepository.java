package com.modela.shipping.adm.repository;

import com.modela.shipping.adm.model.AdmOrganization;
import com.modela.shipping.adm.model.AdmParameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdmParameterRepository extends JpaRepository<AdmParameter, Long> {

    Page<AdmParameter> findAllByOrganization(Pageable pageable, AdmOrganization organization);

    AdmParameter findByParameterCategoryIdAndOrganization_organizationId(Long parameterCategoryId, Long organizationId);

    List<AdmParameter> findByParameterCategoryIdInAndOrganization_organizationId(List<Long> parameterCategoriesIds, Long organizationId);
}
