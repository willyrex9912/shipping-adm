package com.modela.shipping.adm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.modela.shipping.adm.util.ShippingConstant;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
@Table(name = "adm_org_route")
@Data
public class AdmOrgRoute {

    @Id
    @SequenceGenerator(name = "orgRouteIdGenerator", sequenceName = "SEQ_ADM_ORG_ROUTE", allocationSize = 1, initialValue = 5000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orgRouteIdGenerator")
    @Column(name = "org_route_id")
    private Long orgRouteId;

    @Column(name = "organization_id")
    private Long organizationId;

    @Column(name = "route_name")
    private String routeName;

    @Column(name = "route_description")
    private String routeDescription;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ShippingConstant.DATETIME_FORMAT)
    @Column(name = "entry_date")
    private LocalDateTime entryDate;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "route", orphanRemoval = true)
    private List<AdmOrgRouteStep> steps;

    public List<AdmOrgRouteStep> getSteps() {
        if (steps == null) return new ArrayList<>();

        return steps
                .stream()
                .sorted(Comparator.comparingInt(AdmOrgRouteStep::getStep))
                .toList();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        AdmOrgRoute that = (AdmOrgRoute) object;

        return orgRouteId.equals(that.orgRouteId);
    }

    @Override
    public int hashCode() {
        return orgRouteId.hashCode();
    }
}
