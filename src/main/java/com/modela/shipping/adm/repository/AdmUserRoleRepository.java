package com.modela.shipping.adm.repository;

import com.modela.shipping.adm.model.AdmRole;
import com.modela.shipping.adm.model.AdmUser;
import com.modela.shipping.adm.model.AdmUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmUserRoleRepository extends JpaRepository<AdmUserRole, Long> {

    boolean existsByUserAndRole(AdmUser user, AdmRole role);

}
