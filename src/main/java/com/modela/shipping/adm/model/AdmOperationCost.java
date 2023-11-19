package com.modela.shipping.adm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.modela.shipping.adm.util.ShippingConstant;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * The persistent class for the adm_operation_cost database table.
 * @author willyrex
 * @version 1.0.0
 */

@Entity
@Table(name = "adm_operation_cost", schema = "public")
@Data
public class AdmOperationCost {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "operationCostIdGenerator")
    @SequenceGenerator(name = "operationCostIdGenerator", sequenceName = "SEQ_ADM_OPERATION_COST", initialValue = 10000, allocationSize = 1)
    @Column(name = "operation_cost_id")
    private Long operationCostId;

    @Column(name = "organization_id")
    private Long organization;

    @Column(name = "sub_organization_id")
    private Long subOrganization;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_cost")
    private AdmCategory categoryCost;

    @Column(name = "amount", precision = 20, scale = 6)
    private BigDecimal amount;

    @JsonFormat(pattern = ShippingConstant.DATETIME_FORMAT, shape = JsonFormat.Shape.STRING)
    @Column(name = "entry_date")
    private LocalDateTime entryDate;

}
