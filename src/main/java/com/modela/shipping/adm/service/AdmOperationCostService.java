package com.modela.shipping.adm.service;

import com.modela.shipping.adm.dto.AdmOperationCostDto;
import com.modela.shipping.adm.dto.ShippingPage;
import com.modela.shipping.adm.model.AdmOperationCost;
import com.modela.shipping.adm.repository.AdmOperationCostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdmOperationCostService {

    private final AdmOperationCostRepository repository;
    private final ShippingSecurityContext securityContext;

    public ShippingPage<List<AdmOperationCostDto>, Long> findAll(Pageable pageable){
        Page<AdmOperationCost> packagesPage = this.repository.findAll(pageable);
        return ShippingPage.of(
                packagesPage.toList().stream().map(AdmOperationCostDto::new).toList(),
                packagesPage.getTotalElements());
    }

    public AdmOperationCost save(AdmOperationCost entity){
        entity.setOrganization(securityContext.getOrgId());
        entity.setSubOrganization(securityContext.getSubOrgId());
        entity.setEntryDate(LocalDateTime.now());
        return this.repository.save(entity);
    }

    public Optional<AdmOperationCost> findById(Long id){
        return this.repository.findById(id);
    }

}
