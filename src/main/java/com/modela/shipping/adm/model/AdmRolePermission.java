package com.modela.shipping.adm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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

    @JsonBackReference("role-permission")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private AdmRole role;

    @JsonBackReference("permission-role")
    @ManyToOne(fetch = FetchType.LAZY)
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
