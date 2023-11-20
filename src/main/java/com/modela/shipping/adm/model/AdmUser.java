package com.modela.shipping.adm.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.modela.shipping.adm.dto.RolUserDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "adm_user")
@Getter @Setter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdmUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userIdGenerator")
    @SequenceGenerator(name = "userIdGenerator", sequenceName = "SEQ_ADM_USER", initialValue = 5000, allocationSize = 1)
    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private AdmOrganization organization;

    @ManyToOne
    @JoinColumn(name = "sub_organization_id", nullable = true)
    private AdmOrganization subOrganization;

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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<AdmUserRole> userRoles;

    @Transient
    @JsonProperty(value = "roles")
    private List<RolUserDto> roles;
}
