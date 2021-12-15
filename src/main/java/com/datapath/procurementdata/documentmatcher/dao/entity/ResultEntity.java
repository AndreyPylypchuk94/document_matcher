package com.datapath.procurementdata.documentmatcher.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Data
@Entity(name = "results")
public class ResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "text")
    private String value;
    private String words;
    private String labels;
    private String completedLabels;
    private LocalDateTime handleDate;
    private Boolean trash;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "results_cases",
            joinColumns = @JoinColumn(name = "result_id"),
            inverseJoinColumns = @JoinColumn(name = "case_id"))
    private List<CaseEntity> selectedCases = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "result")
    private List<ResultLabelEntity> selectedLabels = new ArrayList<>();

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
