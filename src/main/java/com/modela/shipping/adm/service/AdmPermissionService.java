package com.modela.shipping.adm.service;

import com.modela.shipping.adm.dto.AdmPermissionDto;
import com.modela.shipping.adm.repository.AdmPermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdmPermissionService {

    private final AdmPermissionRepository repository;

    public List<AdmPermissionDto> findAll() {
        var permissions = repository.findAll();
        return permissions
                .stream()
                .map(AdmPermissionDto::new)
                .toList();
    }
}
