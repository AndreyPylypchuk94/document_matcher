package com.datapath.procurementdata.documentmatcher.dao.domain;

import lombok.Data;

@Data
public class MatchingResult {
    private Long id;
    private String title;
    private String[] words;
    private String[] types;
}
