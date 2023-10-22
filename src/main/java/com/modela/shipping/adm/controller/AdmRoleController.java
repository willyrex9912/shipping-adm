package com.modela.shipping.adm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.modela.shipping.adm.dto.AdmRoleDto;
import com.modela.shipping.adm.dto.ShippingPage;
import com.modela.shipping.adm.model.AdmRole;
import com.modela.shipping.adm.service.AdmRoleService;
import com.modela.shipping.adm.util.exception.ShippingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("roles")
@RequiredArgsConstructor
@Slf4j
public class AdmRoleController {

    private final AdmRoleService service;

    @GetMapping
    public ResponseEntity<ShippingPage<List<AdmRoleDto>, Long>> findAll(Pageable pageable) {
        return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdmRole> findById(@PathVariable("id") Long roleId) {
        return service.findById(roleId)
                .map(role -> {
                    var mapper = new ObjectMapper();
                    try {
                        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(role);
                        log.info("json: {}", json);
                    } catch (JsonProcessingException e) {
                        log.error("Error: ", e);
                    }

                    return new ResponseEntity<>(role, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<AdmRole> save(@RequestBody AdmRole role) {
        service.save(role);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdmRole> update(@PathVariable("id") Long id, @RequestBody AdmRole role) throws ShippingException {
        var updatedRole = service.update(id, role);
        return updatedRole == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(HttpStatus.OK);
    }
}
