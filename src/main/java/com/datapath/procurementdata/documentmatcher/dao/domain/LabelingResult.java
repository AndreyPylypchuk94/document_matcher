package com.datapath.procurementdata.documentmatcher.dao.domain;

import lombok.Data;

@Data
public class LabelingResult {
    private Long id;
    private String value;
    private Long[] words;
    private Long[] labels;
}
