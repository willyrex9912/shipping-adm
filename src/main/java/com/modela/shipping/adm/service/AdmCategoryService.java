package com.modela.shipping.adm.service;

import com.modela.shipping.adm.model.AdmCategory;
import com.modela.shipping.adm.repository.AdmCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdmCategoryService {

    private final AdmCategoryRepository repository;

    public AdmCategory findByInternalId(Long internalId) {
        return repository.findByInternalId(internalId);
    }

    public List<AdmCategory> findByParent(Long parentInternalId) {
        return repository.findByParentInternalId(parentInternalId);
    }
}
