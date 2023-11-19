package com.modela.shipping.adm.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GraphDto {

    private Long sourceOrganizationId;
    private List<GraphDto> nodes;

    public GraphDto(Long sourceOrganizationId) {
        this.sourceOrganizationId = sourceOrganizationId;
        this.nodes = new ArrayList<>();
    }
}
