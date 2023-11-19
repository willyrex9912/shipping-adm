package com.modela.shipping.adm.service;

import com.modela.shipping.adm.dto.GraphDto;
import com.modela.shipping.adm.model.AdmOrgRouteStep;
import com.modela.shipping.adm.repository.AdmOrgRouteRepository;
import com.modela.shipping.adm.repository.AdmOrgRouteStepRepository;
import com.modela.shipping.adm.util.exception.ShippingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void findRoute2(Long source, Long target) {
        var root = buildTree(source);
        var list = new ArrayList<List<Long>>();
        list.add(new ArrayList<>(List.of(source)));
        findPath2(root, target, list);

        list
                // .stream()
                // .filter(tmpList -> tmpList.lastIndexOf(target) == tmpList.size() - 1)
                .forEach(tmpList -> {
                    var strPath = String.join(" -> ", tmpList.stream().map(String::valueOf).toList());
                    log.info("path: {}", strPath);
                });
    }

    public GraphDto buildTree(Long sourceOrg) {
        var visited = new HashSet<Long>();
        var root = new GraphDto(sourceOrg);
        addNodes(root, visited);
        return root;
    }

    private void addNodes(GraphDto node, Set<Long> visitedNodes) {
        if (visitedNodes.contains(node.getSourceOrganizationId())) return;
        visitedNodes.add(node.getSourceOrganizationId());

        var nodes = orgRouteStepRepository.findBySourceOrganizationId(node.getSourceOrganizationId())
                .stream()
                .map(AdmOrgRouteStep::getTargetOrganizationId)
                .map(GraphDto::new)
                .toList();

        node.setNodes(nodes);
        nodes.forEach(tmpNode -> addNodes(tmpNode, visitedNodes));
    }

    private void findPath2(GraphDto node, Long target, List<List<Long>> path) {
        var index = path.isEmpty() ? 0 : path.size() - 1;

//        if (node.getSourceOrganizationId().equals(target)) return;

        for (var child : node.getNodes()) {

            var current = path.get(index);
            var newBranch = new ArrayList<>(current);
            path.add(newBranch);
            newBranch.add(child.getSourceOrganizationId());
            if (child.getSourceOrganizationId().equals(target)) continue;
            findPath2(child, target, path);
        }
    }

    private boolean findPath(GraphDto node, Long target, List<Long> path) {
        if (node.getSourceOrganizationId().equals(target)) {
            path.add(node.getSourceOrganizationId());
            return true;
        }

        var found = false;
        for (var tmpNode : node.getNodes()) {
            var list = new ArrayList<>(path);
            var tmp = findPath(tmpNode, target, list);
            found = found || tmp;

            if (found) {
                list.add(tmpNode.getSourceOrganizationId());
                var strPath = String.join(
                        " -> ",
                        list
                                .stream()
                                .map(String::valueOf)
                                .toList()
                );

                log.info(strPath);
            }
        }

        return found;
    }
}
