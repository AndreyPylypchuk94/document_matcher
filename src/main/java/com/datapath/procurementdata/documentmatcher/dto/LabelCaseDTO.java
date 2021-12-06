package com.datapath.procurementdata.documentmatcher.dto;

import lombok.Data;

import java.util.List;

@Data
public class LabelCaseDTO {
    private Long id;
    private List<Long> wordIds;
}
