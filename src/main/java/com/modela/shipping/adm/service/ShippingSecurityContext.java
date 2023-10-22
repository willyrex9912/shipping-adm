package com.modela.shipping.adm.service;

import com.modela.shipping.adm.model.AdmUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ShippingSecurityContext {

    private AdmUserDetails getPrincipal() {
        var token = SecurityContextHolder.getContext().getAuthentication();
        if (token == null) return null;

        if (token.getPrincipal() instanceof AdmUserDetails) {
            return (AdmUserDetails) token.getPrincipal();
        }

        return null;
    }

    public Long getOrgId() {
        var principal = getPrincipal();
        if (principal == null) return null;
        return principal.getOrgId();
    }

    public Long getSubOrgId() {
        var principal = getPrincipal();
        if (principal == null) return null;
        return principal.getSubOrgId();
    }

    public Long userId() {
        var principal = getPrincipal();
        if (principal == null) return null;
        return principal.getUserId();
    }
}
