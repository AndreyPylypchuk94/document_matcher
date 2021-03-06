package com.datapath.procurementdata.documentmatcher.dao.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity(name = "categories")
public class CategoryEntity {
    @Id
    private Integer id;
    private String category;
    private String workflowDirParam;

    @ManyToOne
    @JoinColumn
    private DictionaryEntity dictionary;
}
