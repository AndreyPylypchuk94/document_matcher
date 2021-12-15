package com.datapath.procurementdata.documentmatcher.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResultDTO {
    private Long id;
    private String value;
    private List<Long> wordIds;
    private List<Long> labelIds;
    private Integer categoryId;
    private List<CaseDTO> selectedCases;
    private List<LabelDataDTO> selectedLabels;
}
