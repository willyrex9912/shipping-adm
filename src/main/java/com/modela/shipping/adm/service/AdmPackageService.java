package com.modela.shipping.adm.service;

import com.modela.shipping.adm.dto.AdmPackageDto;
import com.modela.shipping.adm.dto.ShippingPage;
import com.modela.shipping.adm.model.AdmPackage;
import com.modela.shipping.adm.repository.AdmPackageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdmPackageService {

    private final AdmPackageRepository repository;

    public ShippingPage<List<AdmPackageDto>, Long> findAll(Pageable pageable){
        Page<AdmPackage> packagesPage = this.repository.findAll(pageable);
        return ShippingPage.of(
                packagesPage.toList().stream().map(AdmPackageDto::new).toList(),
                packagesPage.getTotalElements());
    }

    public AdmPackage save(AdmPackage entity){
        entity.setEstimatedCost(BigDecimal.valueOf(0.0));
        entity.setPackageCode(UUID.randomUUID().toString().substring(0,2)
                        +LocalDateTime.now().getDayOfYear()
                        +UUID.randomUUID().toString().substring(0,2)
                        +String.valueOf(LocalDateTime.now().getYear()).substring(2,4)
                        +LocalDateTime.now().getMinute()
                        +UUID.randomUUID().toString().charAt(0)
                        +LocalDateTime.now().getHour()
                        +LocalDateTime.now().getSecond()
        );
        entity.setEntryDate(LocalDateTime.now());
        return this.repository.save(entity);
    }

    public Optional<AdmPackage> findById(Long id){
        return this.repository.findById(id);
    }


}
