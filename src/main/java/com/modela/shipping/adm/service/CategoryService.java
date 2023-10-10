package com.modela.shipping.adm.service;

import com.modela.shipping.adm.model.Category;
import com.modela.shipping.adm.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public Category findByInternalId(Long internalId) {
        return repository.findByInternalId(internalId);
    }

    public List<Category> findByParent(Long parentInternalId) {
        return repository.findByParentInternalId(parentInternalId);
    }
}
