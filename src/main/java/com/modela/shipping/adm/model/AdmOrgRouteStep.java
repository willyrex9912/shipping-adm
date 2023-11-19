package com.modela.shipping.adm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "adm_org_route_step")
@Data
public class AdmOrgRouteStep {

    @Id
    @SequenceGenerator(name = "orgRouteStepIdGenerator", sequenceName = "SEQ_ADM_ORG_ROUTE_STEP", allocationSize = 1, initialValue = 5000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orgRouteStepIdGenerator")
    @Column(name = "org_route_step_id")
    private Long orgRouteStepId;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_route_id")
    private AdmOrgRoute route;

    @Column(name = "step_number")
    private Integer step;

    @Column(name = "source_organization_id")
    private Long sourceOrganizationId;

    @Column(name = "target_organization_id")
    private Long targetOrganizationId;

    @Column(name = "average_distance")
    private Double averageDistance;
}
