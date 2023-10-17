package com.modela.shipping.adm.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "adm_organization")
@Getter @Setter
public class AdmOrganization {

    @Id
    @SequenceGenerator(name = "organizationIdGenerator", sequenceName = "SEQ_ADM_ORGANIZATION", initialValue = 5000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organizationIdGenerator")
    @Column(name = "organization_id")
    private Long organizationId;

    @Column(name = "org_name")
    private String orgName;

    @Column(name = "org_description")
    private String orgDescription;

}
