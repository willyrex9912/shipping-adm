package com.modela.shipping.adm.service;

import com.modela.shipping.adm.dto.ShippingPage;
import com.modela.shipping.adm.model.AdmOrganization;
import com.modela.shipping.adm.repository.AdmOrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdmOrganizationService {

    private final AdmOrganizationRepository repository;

    public ShippingPage<List<AdmOrganization>, Long> findAll(Pageable pageable){
        var organizations = repository.findAll(pageable);
        return ShippingPage.of(organizations.toList(), organizations.getTotalElements());
    }

    public AdmOrganization save(AdmOrganization organization) {
        return repository.save(organization);
    }

    public Optional<AdmOrganization> findById(Long id) {
        return repository.findById(id);
    }

}
