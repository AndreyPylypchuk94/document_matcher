package com.datapath.procurementdata.documentmatcher.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LabelDTO {
    private Long id;
    private String label;
    private Integer categoryId;
    private List<CaseDTO> cases;
}
