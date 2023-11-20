package com.modela.shipping.adm.service;

import com.modela.shipping.adm.model.AdmOrgRoute;
import com.modela.shipping.adm.repository.AdmOrgRouteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdmOrgRouteService {

    private final AdmOrgRouteRepository orgRouteRepository;

    public Optional<AdmOrgRoute> findById(Long id) {
        return orgRouteRepository.findById(id);
    }
}
