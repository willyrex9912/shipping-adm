package com.modela.shipping.adm.repository;

import com.modela.shipping.adm.model.AdmVehicleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmVehicleTypeRepository extends JpaRepository<AdmVehicleType, Long> {

    AdmVehicleType findByVehicleCategory_internalId(Long vehicleTypeId);
}
