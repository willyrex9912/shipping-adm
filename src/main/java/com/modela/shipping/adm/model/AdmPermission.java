package com.modela.shipping.adm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "adm_permission")
@Getter
@Setter
public class AdmPermission {

    @Id
    @Column(name = "permission_id")
    private Long permissionId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_permission_id")
    private AdmPermission parentPermission;

    @Column(name = "internal_id")
    private Long internalId;

    @JoinColumn(name = "name")
    private String name;

    @JoinColumn(name = "sref")
    private String sref;

    @JoinColumn(name = "icon")
    private String icon;

    @JoinColumn(name = "priority")
    private Integer priority;
}
