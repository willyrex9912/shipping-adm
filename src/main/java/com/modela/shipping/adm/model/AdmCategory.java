package com.modela.shipping.adm.model;

import com.modela.shipping.adm.util.CategoryEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "adm_category")
@Getter
@Setter
public class AdmCategory {

    @Id
    @SequenceGenerator(name = "categoryIdGenerator", sequenceName = "SEQ_CATEGORY", allocationSize = 1, initialValue = 5000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoryIdGenerator")
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "parent_category_id")
    private Long parentCategoryId;

    @Column(name = "internal_id")
    private Long internalId;

    @Column(name = "description")
    private String description;

    public boolean is(Long otherInternalId) {
        return this.internalId.equals(otherInternalId);
    }

    public boolean is(AdmCategory other) {
        return this.is(other.internalId);
    }

    public boolean is(CategoryEnum categoryEnum) {
        return this.is(categoryEnum.internalId);
    }
}
