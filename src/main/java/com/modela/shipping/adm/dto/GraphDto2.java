package com.modela.shipping.adm.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GraphDto2 {

    private GraphDto2 parent;
    private Long sourceOrganizationId;
    private List<GraphDto2> children;

    public GraphDto2(Long sourceOrganizationId, GraphDto2 parent) {
        this.parent = parent;
        this.sourceOrganizationId = sourceOrganizationId;
    }

    public boolean contains(Long organizationId) {
        System.out.println("checking");
        if (parent == null) return this.sourceOrganizationId.equals(organizationId);

        var aux = parent;
        while (aux != null) {
            System.out.println("parent: " + parent.getSourceOrganizationId());
            if (aux.sourceOrganizationId.equals(organizationId)) return true;
            System.out.println("aux: " + aux.getSourceOrganizationId());
            aux = aux.parent;
        }

        System.out.println("return false");
        return false;
    }
}
