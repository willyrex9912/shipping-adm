package com.modela.shipping.adm.controller;

import com.modela.shipping.adm.model.Category;
import com.modela.shipping.adm.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping("/{internalId}")
    public ResponseEntity<Category> findByInternalId(@PathVariable("internalId") Long internalId) {
        var category = service.findByInternalId(internalId);
        return category == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/by-parent/{parentInternalId}")
    public ResponseEntity<List<Category>> findByParentInternalId(@PathVariable("parentInternalId") Long parentInternalId) {
        return new ResponseEntity<>(service.findByParent(parentInternalId), HttpStatus.OK);
    }
}
