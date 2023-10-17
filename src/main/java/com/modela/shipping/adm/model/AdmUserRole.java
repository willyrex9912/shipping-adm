package com.modela.shipping.adm.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "adm_user_role")
@Getter @Setter
public class AdmUserRole {

    @Id
    @SequenceGenerator(name = "userRoleIdGenerator", sequenceName = "SEQ_ADM_USER_ROLE", initialValue = 5000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userRoleIdGenerator")
    @Column(name = "user_role_id")
    private Long userRoleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private AdmUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private AdmRole role;

    @Column(name = "entry_date")
    private LocalDateTime entryDate;

}
