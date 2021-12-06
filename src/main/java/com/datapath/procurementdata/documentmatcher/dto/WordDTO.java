package com.datapath.procurementdata.documentmatcher.dto;

import lombok.Data;

@Data
public class WordDTO {
    private Long id;
    private String value;
    private String regex;
}
