package com.modela.shipping.adm.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private AdmOrganization organization;

    @Column(name = "capacity")
    private Double capacity;

    @Column(name = "avg_cost_per_km")
    private Double avgCostPerKm;

    @Column(name = "avg_speed")
    private Double avgSpeed;

    @Column(name = "vehicle_category_id")
    private Long vehicleCategoryId;

    @Column(name = "status_category_id")
    private Long statusCategoryId;

}
