package com.modela.shipping.adm.repository;

import com.modela.shipping.adm.model.AdmUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdmUserRepository extends JpaRepository<AdmUser, Long> {

    Optional<AdmUser> findByEmail(String email);
}
