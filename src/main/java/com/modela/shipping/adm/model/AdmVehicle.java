package com.modela.shipping.adm.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "adm_vehicle")
@Getter @Setter @ToString @Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdmVehicle {

    @Id
    @SequenceGenerator(name = "vehicleIdGenerator", sequenceName = "SEQ_ADM_VEHICLE", initialValue = 5000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicleIdGenerator")
    @Column(name = "vehicle_id")
    private Long vehicleId;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private AdmOrganization organization;

    @Column(name = "capacity")
    private Double capacity;

    @Column(name = "avg_cost_per_km")
    private Double avgCostPerKm;

    @Column(name = "category_status_id")
    private Long categoryStatusId;

}
