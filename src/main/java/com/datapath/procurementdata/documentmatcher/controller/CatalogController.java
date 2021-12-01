package com.datapath.procurementdata.documentmatcher.controller;

import com.datapath.procurementdata.documentmatcher.dao.entity.DocumentTypeEntity;
import com.datapath.procurementdata.documentmatcher.service.CatalogWebService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("catalogs")
@AllArgsConstructor
public class CatalogController {

    private final CatalogWebService service;

    @GetMapping("types")
    public List<DocumentTypeEntity> getTypes() {
        return service.getTypes();
    }
}
