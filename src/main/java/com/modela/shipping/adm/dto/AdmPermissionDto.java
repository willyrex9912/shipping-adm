package com.modela.shipping.adm.dto;

import com.modela.shipping.adm.model.AdmPermission;

public record AdmPermissionDto(
        Long permissionId, Long parentPermissionId, Long internalId,
        String name, String sref, String icon, Integer priority
) {

    public AdmPermissionDto(AdmPermission entity) {
        this(
                entity.getPermissionId(), entity.getParentPermission() != null ? entity.getParentPermission().getPermissionId() : null,
                entity.getInternalId(), entity.getName(), entity.getSref(), entity.getIcon(), entity.getPriority()
        );
    }
}
