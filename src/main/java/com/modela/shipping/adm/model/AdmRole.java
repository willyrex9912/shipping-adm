package com.modela.shipping.adm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "adm_role")
@Getter
@Setter
public class AdmRole {

    @Id
    @SequenceGenerator(name = "roleIdGenerator", sequenceName = "SEQ_ADM_ROLE", allocationSize = 1, initialValue = 5000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleIdGenerator")
    @Column(name = "role_id")
    private Long roleId;

    @JsonBackReference("role-organization")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private AdmOrganization organization;

    @Column(name = "sub_organization_id")
    private Long subOrganizationId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "hourly_fee")
    private Double hourlyFee;

    @OneToMany(mappedBy = "role")
    private List<AdmRolePermission> rolePermissions;

    @OneToMany(mappedBy = "role")
    private List<AdmUserRole> userRoles;
}
