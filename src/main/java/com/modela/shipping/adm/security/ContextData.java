package com.modela.shipping.adm.security;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author willyrex
 */

public class ContextData extends UsernamePasswordAuthenticationToken {

    private Long userId;
    private Long orgId;
    private List<String> roles;

    public ContextData(Object principal, Object credentials, Long user, Long org, List<String> roles) {
        super(principal, credentials);
        this.userId = user;
        this.orgId = org;
        this.roles = roles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
