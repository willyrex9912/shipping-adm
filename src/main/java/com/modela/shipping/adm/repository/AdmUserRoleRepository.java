package com.modela.shipping.adm.repository;

import com.modela.shipping.adm.model.AdmUser;
import com.modela.shipping.adm.model.AdmUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdmUserRoleRepository extends JpaRepository<AdmUserRole, Long> {

    List<AdmUserRole> findAllByUser(AdmUser user);

}
