package com.modela.shipping.adm.repository;

import com.modela.shipping.adm.model.AdmTokenCredential;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface AdmTokenCredentialRepository extends JpaRepository<AdmTokenCredential, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE AdmTokenCredential tc SET tc.expirationDate = :newDate WHERE tc.userId = :userId")
    void updateAdmTokenCredentialByUserId(@Param("userId") Long userId,@Param("newDate") LocalDateTime newDate);

    Long countAdmTokenCredentialByUserIdAndExpirationDateAfterAndToken(Long userId, LocalDateTime date, String token);
}
