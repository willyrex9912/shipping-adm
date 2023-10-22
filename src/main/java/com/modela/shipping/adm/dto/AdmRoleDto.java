package com.modela.shipping.adm.dto;

import com.modela.shipping.adm.model.AdmRole;

public record AdmRoleDto(Long roleId, String name, String description, Double hourlyFee) {

    public AdmRoleDto(AdmRole role) {
        this(role.getRoleId(), role.getName(), role.getDescription(), role.getHourlyFee());
    }
}
