package com.modela.shipping.adm.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "adm_vehicle_type")
@Getter @Setter @ToString @Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdmVehicleType {

    @Id
    @SequenceGenerator(name = "vehicleTypeIdGenerator", sequenceName = "SEQ_ADM_VEHICLE_TYPE", initialValue = 5000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicleTypeIdGenerator")
    @Column(name = "vehicle_type_id")
    private Long vehicleTypeId;

    @Column(name = "description")
    private String description;

    @Column(name = "weight_fee")
    private Double weightFee;

    @Column(name = "distance_fee")
    private Double distanceFee;

    @ManyToOne
    @JoinColumn(name = "vehicle_category_id")
    private AdmCategory vehicleCategory;
}
