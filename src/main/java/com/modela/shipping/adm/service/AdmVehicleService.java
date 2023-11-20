package com.modela.shipping.adm.service;

import com.modela.shipping.adm.dto.ShippingPage;
import com.modela.shipping.adm.model.AdmOrganization;
import com.modela.shipping.adm.model.AdmVehicle;
import com.modela.shipping.adm.repository.AdmVehicleRepository;
import com.modela.shipping.adm.util.exception.ShippingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdmVehicleService {

    private final AdmVehicleRepository admVehicleRepository;
    private final ShippingSecurityContext securityContext;

    public ShippingPage<List<AdmVehicle>, Long> findAll(Pageable pageable){
        var organization = AdmOrganization.builder().organizationId(securityContext.getSubOrgId()).build();
        var vehicles = admVehicleRepository.findByOrganizationOrOrganizationIsNull(pageable, organization);
        return ShippingPage.of(vehicles.toList(), vehicles.getTotalElements());
    }

    public AdmVehicle save(AdmVehicle vehicle) {
        return admVehicleRepository.save(vehicle);
    }

    public AdmVehicle update(Long id, AdmVehicle vehicle) {
        var oVehicle = admVehicleRepository.findById(id);

        if (oVehicle.isEmpty())
            throw new ShippingException("Vehicle not found")
                    .withStatus(HttpStatus.NOT_FOUND);

        vehicle.setVehicleId(id);
        return admVehicleRepository.save(vehicle);
    }

    public AdmVehicle findById(Long id) {
        var oVehicle = admVehicleRepository.findById(id);

        if (oVehicle.isEmpty())
            throw new ShippingException("Vehicle not found")
                    .withStatus(HttpStatus.NOT_FOUND);

        return admVehicleRepository.findById(id).orElse(null);
    }
}
