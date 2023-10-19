package com.modela.shipping.adm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "adm_user")
@Getter
@Setter
public class
AdmUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userIdGenerator")
    @SequenceGenerator(name = "userIdGenerator", sequenceName = "SEQ_ADM_USER", initialValue = 5000, allocationSize = 1)
    @Column(name = "user_id")
    private Long userId;

    @JsonBackReference("user-organization")
    @ManyToOne
    @JoinColumn(name = "organization_id")
    private AdmOrganization organization;

    @Column(name = "sub_organization_id")
    private Long subOrganizationId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "unique_identification_code")
    private String cui;

    @OneToMany(mappedBy = "user")
    private List<AdmUserRole> userRoles;
}
