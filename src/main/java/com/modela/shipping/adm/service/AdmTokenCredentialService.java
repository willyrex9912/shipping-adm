package com.modela.shipping.adm.service;

import com.modela.shipping.adm.model.AdmTokenCredential;
import com.modela.shipping.adm.model.AdmUser;
import com.modela.shipping.adm.repository.AdmTokenCredentialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdmTokenCredentialService {

    @Value("${adm.token-valid-time}")
    private Long tokenValidTime;

    private final AdmTokenCredentialRepository tokenCredentialRepository;
    private final TokenService tokenService;

    public String doLogin(AdmUser user, String userAgent) throws IOException {
        String token = this.generateToken(user);
        LocalDateTime tokenGeneration = LocalDateTime.now();
        AdmTokenCredential tokenCredential = new AdmTokenCredential(
                token,
                tokenGeneration,
                tokenGeneration.plusHours(tokenValidTime),
                userAgent,
                user.getUserId()
        );
        this.invalidateAllTokens(user.getUserId());
        this.tokenCredentialRepository.save(tokenCredential);
        return token;
    }

    private String generateToken(AdmUser user) throws IOException {
        List<String> roles = new ArrayList<>();
        //TODO: Add connection to database when role has been implemented.
        roles.add("3");
        return tokenService.generateToken(
                user.getEmail(),
                user.getOrganizationId(),
                user.getUserId(),
                roles
        );
    }

    private void invalidateAllTokens(Long userId){
        this.tokenCredentialRepository.updateAdmTokenCredentialByUserId(userId, LocalDateTime.now());
    }

    public Boolean validToken(Long userId, List<String> roles, String token){
        if (userId == null || roles == null || token == null || token.isBlank()) {
            return false;
        }
        Long activeTokens = this.countActiveTokenByUser(userId, token);
        List<Long> groupsIds = roles.stream().map(Long::parseLong).toList();
        if (groupsIds.isEmpty()) return false;
        Long activeRoles = (long)groupsIds.size(); //TODO: Add connection to database and count roles when role table has been implemented.
        return !(activeTokens.equals(0L) || activeRoles.equals(0L));
    }

    private Long countActiveTokenByUser(Long userId, String token){
        return this.tokenCredentialRepository.countAdmTokenCredentialByUserIdAndExpirationDateAfterAndToken(
                userId, LocalDateTime.now(), token);
    }
}
