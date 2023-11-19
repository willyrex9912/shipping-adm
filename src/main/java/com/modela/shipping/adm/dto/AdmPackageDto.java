package com.modela.shipping.adm.dto;

import com.modela.shipping.adm.model.AdmPackage;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author willyrex
 */

@Data
public class AdmPackageDto {

    private Long packageId;
    private BigDecimal weight;
    private String description;
    private BigDecimal estimatedCost;
    private String sourceOrganizationName;
    private String targetOrganizationName;
    private String sourceCustomerName;
    private String sourceCustomerContact;
    private String targetCustomerName;
    private String targetCustomerContact;
    private String packageCode;

    public AdmPackageDto(AdmPackage entity) {
        this.packageId = entity.getPackageId();
        this.weight = entity.getWeight();
        this.description = entity.getDescription();
        this.estimatedCost = entity.getEstimatedCost();
        this.sourceOrganizationName = entity.getSourceOrganization().getOrgName();
        this.targetOrganizationName = entity.getTargetOrganization().getOrgName();
        this.sourceCustomerName = entity.getSourceCustomerName();
        this.sourceCustomerContact = entity.getSourceCustomerContact();
        this.targetCustomerName = entity.getTargetCustomerName();
        this.targetCustomerContact = entity.getTargetCustomerContact();
        this.packageCode = entity.getPackageCode();
    }

}
