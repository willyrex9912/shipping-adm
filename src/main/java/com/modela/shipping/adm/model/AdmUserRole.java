package com.modela.shipping.adm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "adm_user_role")
@Getter @Setter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdmUserRole {

    @Id
    @SequenceGenerator(name = "userRoleIdGenerator", sequenceName = "SEQ_ADM_USER_ROLE", initialValue = 5000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userRoleIdGenerator")
    @Column(name = "user_role_id")
    private Long userRoleId;

    @JsonBackReference("user-role")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AdmUser user;

    @JsonBackReference("role-user")
    @ManyToOne
    @JoinColumn(name = "role_id")
    private AdmRole role;

    @Column(name = "entry_date")
    private LocalDateTime entryDate = LocalDateTime.now();

}
