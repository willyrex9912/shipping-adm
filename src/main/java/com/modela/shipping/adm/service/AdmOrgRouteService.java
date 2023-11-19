package com.modela.shipping.adm.service;

import com.modela.shipping.adm.model.AdmOrgRouteStep;
import com.modela.shipping.adm.repository.AdmOrgRouteRepository;
import com.modela.shipping.adm.repository.AdmOrgRouteStepRepository;
import com.modela.shipping.adm.util.exception.ShippingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdmOrgRouteService {

    private final AdmOrgRouteRepository orgRouteRepository;
    private final AdmOrgRouteStepRepository orgRouteStepRepository;

    public void findRoute(Long srcOrg, Long targetOrg) throws ShippingException {
        // TODO: main routes (src -> {... ->} target {-> ...})
        var possiblyMainRoutes = orgRouteRepository.findByOrganizationId(srcOrg);
        for (var route : possiblyMainRoutes) {
            var targetOpt = route
                    .getSteps()
                    .stream()
                    .filter(r -> r.getTargetOrganizationId().equals(targetOrg))
                    .findFirst();

            if (targetOpt.isEmpty()) continue;
            var target = targetOpt.get();

            log.info("possibly complete route: {}", route.getRouteName());
            var finalIndex = route.getSteps().indexOf(target);

            var newRoute = route
                    .getSteps()
                    .subList(0, finalIndex + 1)
                    .stream()
                    .map(AdmOrgRouteStep::getSourceOrganizationId)
                    .map(String::valueOf)
                    .toList();

            var strRoute = String.join(" -> ", newRoute).concat(" -> ").concat(String.valueOf(target.getTargetOrganizationId()));
            log.info("steps: {}", strRoute);
        }

        // TODO: part of a route (start -> .. -> src -> {... ->} target {-> ...})
        var possiblyPartRoutes = orgRouteStepRepository.findByTargetOrganizationId(targetOrg);
        for (var step : possiblyPartRoutes) {
            var route = step.getRoute();
            var srcOpt = route
                    .getSteps()
                    .stream()
                    .filter(r -> r.getSourceOrganizationId().equals(srcOrg))
                    .findFirst();

            if (srcOpt.isEmpty()) continue;
            var source = srcOpt.get();

            var targetOpt = route
                    .getSteps()
                    .stream()
                    .filter(r -> r.getTargetOrganizationId().equals(targetOrg) && r.getStep() >= source.getStep())
                    .findFirst();

            if (targetOpt.isEmpty()) continue;
            var target = targetOpt.get();

            var startIndex = route
                    .getSteps()
                    .indexOf(source);

            var endIndex = route
                    .getSteps()
                    .indexOf(target);

            var newRoute = route
                    .getSteps()
                    .subList(startIndex, endIndex + 1)
                    .stream()
                    .map(AdmOrgRouteStep::getSourceOrganizationId)
                    .map(String::valueOf)
                    .toList();

            if (newRoute.isEmpty()) continue;
            log.info("possibly part route: {}", route.getRouteName());

            var strRoute = String.join(" -> ", newRoute).concat(" -> ").concat(String.valueOf(target.getTargetOrganizationId()));
            log.info("steps: {}", strRoute);
        }
    }
}
