package com.modela.shipping.adm.service;

import com.modela.shipping.adm.dto.TreeNodeDto;
import com.modela.shipping.adm.model.AdmOrgRouteStep;
import com.modela.shipping.adm.model.AdmPackageRoute;
import com.modela.shipping.adm.repository.AdmOrgRouteStepRepository;
import com.modela.shipping.adm.util.CategoryEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdmPackageRouteService {

    private final AdmOrgRouteStepRepository orgRouteStepRepository;
    private final AdmCategoryService categoryService;

    public List<List<AdmPackageRoute>> findRoutes(Long source, Long target) {
        var root = buildTree(source);
        var paths = new ArrayList<List<Long>>();
        paths.add(new ArrayList<>(List.of(source)));
        findPath(root, target, paths);

        return paths
                .stream()
                .filter(path -> path.lastIndexOf(target) == path.size() - 1)
                .map(this::createPackageRoute)
                // TODO: set estimated cost and time here
                .peek(route -> log.info(route.toString()))
                .toList();
    }

    private void setEstimatedDistanceAndTimeAndCost(AdmPackageRoute packageRoute) {
        // cost per km = (gas cost (Q/L)) * (gas consume (L/100K)) / (100 * vehicle weight(kg))

        var similarRoutes = orgRouteStepRepository.findBySourceOrganizationIdAndTargetOrganizationId(packageRoute.getSourceOrganizationId(), packageRoute.getTargetOrganizationId());

        var totalTrips = similarRoutes
                .stream()
                .mapToLong(AdmOrgRouteStep::getNumberOfTrips)
                .sum();

        var totalDistance = getTotalByFunction(similarRoutes, AdmOrgRouteStep::getAverageDistance);
        var totalTime = getTotalByFunction(similarRoutes, AdmOrgRouteStep::getAverageTime);
        var totalAvgCost = getTotalByFunction(similarRoutes, AdmOrgRouteStep::getAverageCostPerKm);

        packageRoute.setEstimatedDistance(totalDistance.divide(new BigDecimal(totalTrips), 2, RoundingMode.HALF_UP));
        packageRoute.setEstimatedTime(totalTime.divide(new BigDecimal(totalTrips), 2, RoundingMode.HALF_UP));
    }

    private BigDecimal getTotalByFunction(List<AdmOrgRouteStep> steps, Function<AdmOrgRouteStep, BigDecimal> transformer) {
        return steps
                .stream()
                .map(transformer)
                .reduce(BigDecimal::add)
                .orElse(new BigDecimal(1));
    }

    private TreeNodeDto buildTree(Long source) {
        var root = new TreeNodeDto(source, null);
        root.setChildren(addChildren(root));
        return root;
    }

    private List<TreeNodeDto> addChildren(TreeNodeDto node) {
        var nodes = orgRouteStepRepository.findBySourceOrganizationId(node.getSourceOrganizationId())
                .stream()
                .filter(step -> !node.contains(step.getTargetOrganizationId()))
                .map(step -> new TreeNodeDto(step.getTargetOrganizationId(), node))
                .toList();

        nodes.forEach(tmpNode -> tmpNode.setChildren(addChildren(tmpNode)));
        return nodes;
    }

    private void findPath(TreeNodeDto node, Long target, List<List<Long>> paths) {
        var index = paths.isEmpty() ? 0 : paths.size() - 1;
        for (var child : node.getChildren()) {
            var currentPath = paths.get(index);
            var newPath = new ArrayList<>(currentPath);
            paths.add(newPath);
            newPath.add(child.getSourceOrganizationId());
            if (child.getSourceOrganizationId().equals(target)) continue;
            findPath(child, target, paths);
        }
    }

    private List<AdmPackageRoute> createPackageRoute(List<Long> paths) {
        var prsPending = categoryService.findByInternalId(CategoryEnum.PRS_PENDING.internalId);
        var routes = new ArrayList<AdmPackageRoute>();
        IntStream.range(0, paths.size() - 1)
                .forEach(index -> {
                    var packageRoute = new AdmPackageRoute();
                    packageRoute.setSourceOrganizationId(paths.get(index));
                    packageRoute.setTargetOrganizationId(paths.get(index + 1));
                    packageRoute.setStepNumber(routes.size());
                    packageRoute.setCategoryPackageRouteStatus(prsPending);
                    packageRoute.setEstimatedCost(new BigDecimal(0));
                    packageRoute.setEstimatedTime(new BigDecimal(0));
                    packageRoute.setEstimatedDistance(new BigDecimal(0));
                    routes.add(packageRoute);
                });

        return routes;
    }
}
