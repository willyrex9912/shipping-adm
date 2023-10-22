package com.modela.shipping.adm.service;

import com.modela.shipping.adm.model.AdmRole;
import com.modela.shipping.adm.model.AdmUser;
import com.modela.shipping.adm.model.AdmUserRole;
import com.modela.shipping.adm.security.KeyLoader;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${adm.token-valid-time}")
    private Integer tokenValidTime;

    @Value("${adm.token-private-key}")
    private String privateKeyName;

    public String generateToken(AdmUser user, List<AdmUserRole> roles) throws IOException {
        var claims = buildClaims(user, roles);
        return createToken(claims, user.getEmail());
    }

    private Map<String, Object> buildClaims(AdmUser user, List<AdmUserRole> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("org", user.getOrganization().getOrganizationId());
        claims.put("subOrg", user.getSubOrganizationId());
        claims.put("user", user.getUserId());
        claims.put("roles", roles.stream()
                .map(AdmUserRole::getRole)
                .map(AdmRole::getRoleId)
                .map(String::valueOf)
                .toList());
        return claims;
    }

    private String createToken(Map<String, Object> claims, String userName) throws IOException {
        return Jwts.builder()
                .setClaims(claims)
                .setId("w" + UUID.randomUUID().getLeastSignificantBits())
                .setSubject(userName)
                .setAudience("CUNOC")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(Date.from(Instant.now().plus(tokenValidTime, ChronoUnit.DAYS)))
                .signWith(KeyLoader.loadPrivateKey(privateKeyName))
                .compact();
    }

    public String extractUsername(String token) throws IOException {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) throws IOException {
        return extractClaim(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) throws IOException {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) throws IOException {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Claims extractAllClaims(String token) throws IOException {
        return Jwts
                .parserBuilder()
                .setSigningKey(KeyLoader.loadPrivateKey(privateKeyName))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) throws IOException {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
}
