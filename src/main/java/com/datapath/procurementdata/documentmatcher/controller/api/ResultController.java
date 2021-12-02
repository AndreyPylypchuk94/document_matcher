package com.datapath.procurementdata.documentmatcher.controller.api;

import com.datapath.procurementdata.documentmatcher.dao.domain.MatchingResult;
import com.datapath.procurementdata.documentmatcher.dto.request.UpdateResultRequest;
import com.datapath.procurementdata.documentmatcher.service.ResultWebService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("results")
public class ResultController {

    private final ResultWebService service;

    @GetMapping
    public List<MatchingResult> get() {
        return service.get();
    }

    @PutMapping
    public void update(@Valid @RequestBody UpdateResultRequest request) {
        service.update(request.getUpdates());
    }
}
