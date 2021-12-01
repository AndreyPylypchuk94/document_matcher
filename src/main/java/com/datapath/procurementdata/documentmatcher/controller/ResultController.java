package com.datapath.procurementdata.documentmatcher.controller;

import com.datapath.procurementdata.documentmatcher.dao.domain.MatchingResult;
import com.datapath.procurementdata.documentmatcher.service.ResultWebService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("results")
@AllArgsConstructor
public class ResultController {

    private final ResultWebService service;

    @GetMapping
    public List<MatchingResult> get() {
        return service.get();
    }
}
