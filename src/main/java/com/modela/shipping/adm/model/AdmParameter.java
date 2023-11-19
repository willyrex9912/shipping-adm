package com.modela.shipping.adm.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "adm_parameter")
@Getter @Setter @ToString @Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdmParameter {
    @Id
    @SequenceGenerator(name = "parameterIdGenerator", sequenceName = "SEQ_ADM_PARAMETER", allocationSize = 1, initialValue = 5000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parameterIdGenerator")
    @Column(name = "parameter_id")
    private Long parameterId;

    @Column(name = "value")
    private String value;

    @Column(name = "description")
    private String description;

    @Column(name = "category_parameter_id")
    private Long categoryParameterId;
}
