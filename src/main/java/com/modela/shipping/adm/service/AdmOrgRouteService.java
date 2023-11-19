package com.modela.shipping.adm.service;

import com.modela.shipping.adm.model.AdmOrgRouteStep;
import com.modela.shipping.adm.repository.AdmOrgRouteRepository;
import com.modela.shipping.adm.util.exception.ShippingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdmOrgRouteService {

    private final AdmOrgRouteRepository orgRouteRepository;

    public void findRoute(Long srcOrg, Long targetOrg) throws ShippingException {
        var possiblyMainRoutes = orgRouteRepository.findByOrganizationId(srcOrg);
        // TODO: find routes to targetOrg
        for (var route : possiblyMainRoutes) {
            var routesToTarget = route
                    .getSteps()
                    .stream()
                    .anyMatch(step -> step.getTargetOrganizationId().equals(targetOrg));

            if (routesToTarget) {
                var builder = new StringBuilder();
                log.info("possibly route: {}", route.getRouteName());
                log.info("steps:");
                for (AdmOrgRouteStep step : route.getSteps()) {
                    builder.append(step.getSourceOrganizationId());
                    if (step.getTargetOrganizationId().equals(targetOrg)) {
                        builder
                                .append(" -> ")
                                .append(step.getTargetOrganizationId());
                        break;
                    }

                    builder.append(" -> ");
                }

                log.info("route: {}", builder);
            }
        }
    }
}
