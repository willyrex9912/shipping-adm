package com.modela.shipping.adm.service;

import com.modela.shipping.adm.security.KeyLoader;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * Service to build token.
 * @author willyrex
 * @version 1.0
 */

@Service
@Slf4j
public class TokenService {

    @Value("${adm.token-valid-time}")
    private Long tokenValidTime;

    @Value("${adm.token-private-key}")
    private String privateKeyName;

    public String generateToken(String username, Long org, Long user, List<String> roles) throws IOException {
        return Jwts.builder()
                .setClaims(this.buildClaims(org, user, roles))
                .setId("w"+UUID.randomUUID().getLeastSignificantBits())
                .setSubject(username)
                .setAudience("CUNOC")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (tokenValidTime * 60 * 60 * 1000)))
                .signWith(KeyLoader.loadPrivateKey(privateKeyName))
                .compact()
                ;
    }

    private Map<String, Object> buildClaims(Long org, Long user, List<String> roles){
        Map<String, Object> claims = new HashMap<>();
        claims.put("org", org);
        claims.put("user", user);
        claims.put("roles", roles);
        return claims;
    }
}
