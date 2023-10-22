package com.modela.shipping.adm.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

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

    @Column(name = "organization_id")
    private Long organizationId;

    @Column(name = "sub_organization_id")
    private Long subOrganizationId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "hourly_fee")
    private Double hourlyFee;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "role", orphanRemoval = true)
    private List<AdmRolePermission> rolePermissions;
}
