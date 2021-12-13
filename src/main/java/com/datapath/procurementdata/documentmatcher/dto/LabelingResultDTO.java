package com.datapath.procurementdata.documentmatcher.dto;

import lombok.Data;

import java.util.List;

@Data
public class LabelingResultDTO {
    private Long id;
    private String value;
    private List<Long> wordIds;
    private List<Long> labelIds;
    private Integer labelCategoryId;
}
