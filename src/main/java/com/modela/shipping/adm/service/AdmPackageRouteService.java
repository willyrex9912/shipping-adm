package com.modela.shipping.adm.service;

import com.modela.shipping.adm.dto.TreeNodeDto;
import com.modela.shipping.adm.model.AdmOrgRouteStep;
import com.modela.shipping.adm.model.AdmPackageRoute;
import com.modela.shipping.adm.repository.AdmOrgRouteStepRepository;
import com.modela.shipping.adm.repository.AdmVehicleTypeRepository;
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
    private final AdmParameterService parameterService;
    private final AdmVehicleTypeRepository vehicleTypeRepository;

    public List<AdmPackageRoute> findRoutes(Long source, Long target, BigDecimal packageWeight) {
        var root = buildTree(source);
        var paths = new ArrayList<List<Long>>();
        paths.add(new ArrayList<>(List.of(source)));
        findPath(root, target, paths);

        var routes = paths
                .stream()
                .filter(path -> path.lastIndexOf(target) == path.size() - 1)
                .map(this::createPackageRoute)
                // TODO: set estimated cost and time here
                .peek(route -> log.info(route.toString()))
                .toList();

        var bestRoute = new ArrayList<AdmPackageRoute>();
        routes.forEach(route -> route.forEach(subRoute -> this.setEstimatedDistanceAndTimeAndCost(subRoute, packageWeight)));

        BigDecimal currentValue = BigDecimal.valueOf(Double.MAX_VALUE);
        for (var route: routes) {
            BigDecimal tmpValue = getResultByFunction(route, AdmPackageRoute::getEstimatedTime);
            if (tmpValue.compareTo(currentValue) < 0) {
                currentValue = tmpValue;
                bestRoute.clear();
                bestRoute.addAll(route);
            } else {
                // TODO: print discard route here
                printDiscardRoute(route, tmpValue);
            }
        }

        if (currentValue.compareTo(BigDecimal.valueOf(Double.MAX_VALUE)) != 0) log.info("best value {}", currentValue);
        return bestRoute;
    }

    private void printDiscardRoute(List<AdmPackageRoute> route, BigDecimal value) {
        var tmpStrRoute = route
                .stream()
                .map(AdmPackageRoute::getSourceOrganizationId)
                .map(String::valueOf)
                .toList();

        var strRoutes = new ArrayList<>(tmpStrRoute);
        strRoutes.add(String.valueOf(route.get(route.size() - 1).getTargetOrganizationId()));
        log.warn("discard route: {}, value: {}", String.join(" -> ", strRoutes), value);
    }

    private BigDecimal getResultByFunction(List<AdmPackageRoute> steps, Function<AdmPackageRoute, BigDecimal> transformer) {
        return steps
                .stream()
                .map(transformer)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.valueOf(Long.MAX_VALUE));
    }

    private void setEstimatedDistanceAndTimeAndCost(AdmPackageRoute packageRoute, BigDecimal packageWeight) {
        // sent cost = (package_weight * weight_fee) + (distance * distance_fee) + (average_time_in_source * cost_of_storage)
        var similarRoutes = orgRouteStepRepository.findBySourceOrganizationIdAndTargetOrganizationId(packageRoute.getSourceOrganizationId(), packageRoute.getTargetOrganizationId());

        // TODO: find type of vehicle most used in the source here
        var vehicleType = vehicleTypeRepository.findByVehicleCategory_internalId(CategoryEnum.VC_MEDIUM.internalId);

        var totalTrips = similarRoutes
                .stream()
                .mapToLong(AdmOrgRouteStep::getNumberOfTrips)
                .sum();

        var totalDistance = getTotalByFunction(similarRoutes, AdmOrgRouteStep::getAverageDistance);
        var totalTime = getTotalByFunction(similarRoutes, AdmOrgRouteStep::getAverageTime);
        var totalAvgCost = getTotalByFunction(similarRoutes, AdmOrgRouteStep::getAverageCostPerKm);

        var estimatedDistance = totalDistance.divide(new BigDecimal(totalTrips), 2, RoundingMode.HALF_UP);
        var estimatedTime = totalTime.divide(new BigDecimal(totalTrips), 2, RoundingMode.HALF_UP);

        var weightTotalFee = packageWeight.multiply(vehicleType.getWeightFee());
        var distanceTotalFee = packageWeight.multiply(vehicleType.getDistanceFee());

        var estimatedCost = weightTotalFee.add(distanceTotalFee);

        packageRoute.setEstimatedDistance(estimatedDistance);
        packageRoute.setEstimatedTime(estimatedTime);
        packageRoute.setEstimatedCost(estimatedCost);
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
