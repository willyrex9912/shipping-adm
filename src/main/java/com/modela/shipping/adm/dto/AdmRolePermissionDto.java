package com.modela.shipping.adm.dto;

import com.modela.shipping.adm.model.AdmRolePermission;
import lombok.Data;

/**
 * @author willyrex
 */

@Data
public class AdmRolePermissionDto {

    private Long permissionId;
    private Long internalId;
    private Boolean createPermission;
    private Boolean deletePermission;
    private Boolean readPermission;
    private Boolean updatePermission;

    public AdmRolePermissionDto(AdmRolePermission entity) {
        this.permissionId = entity.getPermission().getPermissionId();
        this.createPermission = entity.getCreatePermission();
        this.deletePermission = entity.getDeletePermission();
        this.readPermission = entity.getReadPermission();
        this.updatePermission = entity.getUpdatePermission();
        this.internalId = entity.getPermission().getInternalId();
    }
}
