package com.datapath.procurementdata.documentmatcher.controller.api;

import com.datapath.procurementdata.documentmatcher.dto.ResultDTO;
import com.datapath.procurementdata.documentmatcher.dto.request.SaveResultBatchRequest;
import com.datapath.procurementdata.documentmatcher.service.ResultWebService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("results")
public class ResultController {

    private final ResultWebService service;

    @GetMapping
    public List<ResultDTO> get(@RequestParam(defaultValue = "false") boolean processed,
                               @RequestParam(defaultValue = "") List<Long> labelIds) {
        if (processed)
            return service.getProcessed(labelIds);
        return service.get();
    }

    @PutMapping
    public void save(@Valid @RequestBody SaveResultBatchRequest request) {
        service.save(request);
    }
}
