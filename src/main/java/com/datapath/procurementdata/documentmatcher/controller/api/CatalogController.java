package com.datapath.procurementdata.documentmatcher.controller.api;

import com.datapath.procurementdata.documentmatcher.dto.CategoryDTO;
import com.datapath.procurementdata.documentmatcher.dto.LabelDTO;
import com.datapath.procurementdata.documentmatcher.dto.WordDTO;
import com.datapath.procurementdata.documentmatcher.dto.request.SaveLabelRequest;
import com.datapath.procurementdata.documentmatcher.dto.request.SaveWordRequest;
import com.datapath.procurementdata.documentmatcher.service.CatalogWebService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("catalogs")
public class CatalogController {

    private final CatalogWebService service;

    @GetMapping("labels")
    public List<LabelDTO> getLabels(@RequestParam int categoryId) {
        return service.getLabels(categoryId);
    }

    @PostMapping("labels")
    public LabelDTO saveLabel(@RequestBody @Valid SaveLabelRequest request) {
        return service.saveLabel(request);
    }

    @GetMapping("words")
    public List<WordDTO> getWords(@RequestParam int categoryId) {
        return service.getWords(categoryId);
    }

    @PostMapping("words")
    public WordDTO saveWord(@RequestBody @Valid SaveWordRequest request) {
        return service.saveWord(request);
    }

    @GetMapping("label-categories")
    public List<CategoryDTO> getLabelCategories() {
        return service.getLabelCategories();
    }
}
