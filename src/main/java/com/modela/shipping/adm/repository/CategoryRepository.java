package com.modela.shipping.adm.repository;

import com.modela.shipping.adm.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByInternalId(Long internalId);

    List<Category> findByParentCategoryId(Long parentCategoryId);

    default List<Category> findByParentInternalId(Long parentInternalId) {
        var parent = this.findByInternalId(parentInternalId);
        if (parent == null) return new ArrayList<>();

        return this.findByParentCategoryId(parent.getCategoryId());
    }
}
