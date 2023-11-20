package com.modela.shipping.adm.dto;

import lombok.Data;

import java.util.List;

@Data
public class TreeNodeDto {

    private final TreeNodeDto parent;
    private final Long sourceOrganizationId;
    private List<TreeNodeDto> children;

    public TreeNodeDto(Long sourceOrganizationId, TreeNodeDto parent) {
        this.sourceOrganizationId = sourceOrganizationId;
        this.parent = parent;
    }

    public boolean contains(Long organizationId) {
        if (parent == null) return this.sourceOrganizationId.equals(organizationId);

        var aux = this.parent;
        while (aux != null) {
            if (aux.sourceOrganizationId.equals(organizationId)) return true;
            aux = aux.parent;
        }
        return false;
    }
}
