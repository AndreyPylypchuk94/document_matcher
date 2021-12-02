package com.datapath.procurementdata.documentmatcher.controller.api;

import com.datapath.procurementdata.documentmatcher.dao.entity.DocumentTypeEntity;
import com.datapath.procurementdata.documentmatcher.dao.entity.WordEntity;
import com.datapath.procurementdata.documentmatcher.dto.CreateWordRequest;
import com.datapath.procurementdata.documentmatcher.dto.SaveTypeRequest;
import com.datapath.procurementdata.documentmatcher.service.CatalogWebService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("catalogs")
public class CatalogController {

    private final CatalogWebService service;

    @GetMapping("types")
    public List<DocumentTypeEntity> getTypes() {
        return service.getTypes();
    }

    @PostMapping("types")
    public DocumentTypeEntity saveType(@RequestBody @Valid SaveTypeRequest request) {
        return service.saveType(request);
    }

    @GetMapping("words")
    public List<WordEntity> getWords() {
        return service.getWords();
    }

    @PostMapping("words")
    @ResponseStatus(CREATED)
    public List<WordEntity> createWord(@RequestBody @Valid CreateWordRequest request) {
        return service.createWord(request);
    }
}
