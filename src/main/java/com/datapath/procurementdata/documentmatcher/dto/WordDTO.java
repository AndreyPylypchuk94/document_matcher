package com.datapath.procurementdata.documentmatcher.dto;

import lombok.Data;

import java.util.Set;

@Data
public class WordDTO {
    private Long id;
    private String word;
    private Set<String> regexes;
}
