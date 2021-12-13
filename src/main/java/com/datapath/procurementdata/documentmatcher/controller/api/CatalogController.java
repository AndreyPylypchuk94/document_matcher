package com.datapath.procurementdata.documentmatcher.controller.api;

import com.datapath.procurementdata.documentmatcher.dto.LabelCategoryDTO;
import com.datapath.procurementdata.documentmatcher.dto.LabelDTO;
import com.datapath.procurementdata.documentmatcher.dto.WordDTO;
import com.datapath.procurementdata.documentmatcher.dto.request.CreateWordRequest;
import com.datapath.procurementdata.documentmatcher.dto.request.SaveLabelRequest;
import com.datapath.procurementdata.documentmatcher.service.CatalogWebService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("catalogs")
public class CatalogController {

    private final CatalogWebService service;

    @GetMapping("labels")
    public List<LabelDTO> getLabels() {
        return service.getLabels();
    }

    @PostMapping("labels")
    public LabelDTO saveLabel(@RequestBody @Valid SaveLabelRequest request) {
        return service.saveLabel(request);
    }

    @GetMapping("words")
    public List<WordDTO> getWords() {
        return service.getWords();
    }

    @PostMapping("words")
    @ResponseStatus(CREATED)
    public List<WordDTO> createWord(@RequestBody @Valid CreateWordRequest request) {
        return service.createWord(request);
    }

    @GetMapping("label-categories")
    public List<LabelCategoryDTO> getLabelCategories() {
        return service.getLabelCategories();
    }
}
