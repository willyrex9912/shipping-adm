package com.modela.shipping.adm.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "adm_organization")
@Getter @Setter @ToString @Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdmOrganization {

    @Id
    @SequenceGenerator(name = "organizationIdGenerator", sequenceName = "SEQ_ADM_ORGANIZATION", initialValue = 2500, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organizationIdGenerator")
    @Column(name = "organization_id")
    private Long organizationId;

    @Column(name = "parent_organization_id")
    private Long parentOrganizationId;

    @Column(name = "org_name")
    private String orgName;

    @Column(name = "org_description")
    private String orgDescription;

    @OneToMany(mappedBy = "organization")
    private List<AdmUser> users;
}
