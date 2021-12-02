package com.datapath.procurementdata.documentmatcher.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MatchingCaseDTO {
    private Long id;
    private List<Long> wordIds = new ArrayList<>();
}
