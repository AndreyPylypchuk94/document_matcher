package com.datapath.procurementdata.documentmatcher.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "results_labels")
public class ResultLabelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "label_id")
    private LabelEntity label;
}
