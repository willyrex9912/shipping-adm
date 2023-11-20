package com.modela.shipping.adm.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

@Getter
public class AdmUserDetails implements UserDetails {

    private final String username;
    private final String password;
    private final Long userId;
    private final Long orgId;
    private final Long subOrgId;

    public AdmUserDetails(AdmUser admUser) {
        this.username = admUser.getEmail();
        this.password = admUser.getPassword();
        this.userId = admUser.getUserId();
        this.orgId = admUser.getOrganization().getOrganizationId();
        this.subOrgId = Objects.isNull(admUser.getSubOrganization()) ? 0 : admUser.getSubOrganization().getOrganizationId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
