package com.modela.shipping.adm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.modela.shipping.adm.util.ShippingConstant;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The persistent class for the adm_package database table.
 * @author willyrex
 * @version 1.0.0
 */

@Entity
@Table(name = "adm_package", schema = "public")
@Data
public class AdmPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "packageIdGenerator")
    @SequenceGenerator(name = "packageIdGenerator", sequenceName = "SEQ_ADM_PACKAGE", initialValue = 10000, allocationSize = 1)
    @Column(name = "package_id")
    private Long packageId;

    @Column(name = "weight", precision = 5, scale = 2)
    private BigDecimal weight;

    @Size(max = 250)
    @Column(name = "description")
    private String description;

    @JsonFormat(pattern = ShippingConstant.DATETIME_FORMAT, shape = JsonFormat.Shape.STRING)
    @Column(name = "entry_date")
    private LocalDateTime entryDate;

    @Column(name = "estimated_cost", precision = 20, scale = 6)
    private BigDecimal estimatedCost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_organization_id")
    private AdmOrganization sourceOrganization;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_organization_id")
    private AdmOrganization targetOrganization;

    @Size(max = 100)
    @Column(name = "source_customer_name")
    private String sourceCustomerName;

    @Size(max = 150)
    @Column(name = "source_customer_contact")
    private String sourceCustomerContact;

    @Size(max = 100)
    @Column(name = "target_customer_name")
    private String targetCustomerName;

    @Size(max = 150)
    @Column(name = "target_customer_contact")
    private String targetCustomerContact;

    @Size(max = 150)
    @Column(name = "package_code")
    private String packageCode;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pkg", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<AdmPackageRoute> packageRoute;
}
