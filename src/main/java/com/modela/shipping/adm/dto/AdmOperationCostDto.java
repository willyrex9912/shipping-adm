package com.modela.shipping.adm.dto;

import com.modela.shipping.adm.model.AdmCategory;
import com.modela.shipping.adm.model.AdmOperationCost;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author willyrex
 * @version 1.0.0
 */

@Data
public class AdmOperationCostDto {

    private Long operationCostId;
    private AdmCategory categoryCost;
    private BigDecimal amount;
    private LocalDateTime entryDate;

    public AdmOperationCostDto(AdmOperationCost entity) {
        this.operationCostId = entity.getOperationCostId();
        this.categoryCost = entity.getCategoryCost();
        this.amount = entity.getAmount();
        this.entryDate = entity.getEntryDate();
    }

}
