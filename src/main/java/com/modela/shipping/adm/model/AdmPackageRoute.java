package com.modela.shipping.adm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.modela.shipping.adm.util.ShippingConstant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Locale;

@Entity
@Table(name = "package_route")
@Data
public class AdmPackageRoute {

    @Id
    @SequenceGenerator(name = "packageRouteIdGenerator", sequenceName = "SEQ_ADM_PACKAGE_ROUTE", allocationSize = 1, initialValue = 5000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "packageRouteIdGenerator")
    @Column(name = "package_route_id")
    private Long packageRouteId;

    @Column(name = "vehicle_id")
    private Long vehicleId;

    @Column(name = "package_id")
    private Long packageId;

    @Column(name = "step_number")
    private Integer stepNumber;

    @Column(name = "source_organization_id")
    private Long sourceOrganizationId;

    @Column(name = "target_organization_id")
    private Long targetOrganizationId;

    @Column(name = "estimated_time")
    private BigDecimal estimatedTime;

    @Column(name = "estimated_cost")
    private BigDecimal estimatedCost;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ShippingConstant.DATETIME_FORMAT)
    @Column(name = "entry_date")
    private LocalDateTime entryDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ShippingConstant.DATETIME_FORMAT)
    @Column(name = "start_date")
    private LocalDateTime startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ShippingConstant.DATETIME_FORMAT)
    @Column(name = "end_date")
    private LocalDateTime endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_package_route_status")
    private AdmCategory categoryPackageRouteStatus;
}
