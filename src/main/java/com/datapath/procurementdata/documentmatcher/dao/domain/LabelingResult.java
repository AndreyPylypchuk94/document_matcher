package com.datapath.procurementdata.documentmatcher.dao.domain;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Data
public class LabelingResult {
    private Long id;
    private String value;
    private String words;
    private String labels;
    private String completedLabels;
    private Integer labelCategoryId;

    public List<Long> getWords() {
        return toCollection(words);
    }

    public List<Long> getLabels() {
        return toCollection(labels);
    }

    public List<Long> getCompletedLabels() {
        return toCollection(completedLabels);
    }

    private List<Long> toCollection(String value) {
        if (isEmpty(value)) return null;
        return Arrays.stream(value.split(","))
                .map(Long::parseLong)
                .collect(toList());
    }
}
