package com.modela.shipping.adm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "adm_role_permission")
@Getter
@Setter
public class AdmRolePermission {

    @Id
    @SequenceGenerator(name = "rolePermissionIdGenerator", sequenceName = "SEQ_ADM_ROLE_PERMISSION", allocationSize = 1, initialValue = 5000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rolePermissionIdGenerator")
    @Column(name = "role_permission_id")
    private Long rolePermissionId;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private AdmRole role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "permission_id")
    private AdmPermission permission;

    @Column(name = "create_permission")
    private Boolean createPermission;

    @Column(name = "read_permission")
    private Boolean readPermission;

    @Column(name = "update_permission")
    private Boolean updatePermission;

    @Column(name = "delete_permission")
    private Boolean deletePermission;
}
