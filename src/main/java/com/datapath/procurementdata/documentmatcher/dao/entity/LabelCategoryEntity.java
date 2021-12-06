package com.datapath.procurementdata.documentmatcher.dao.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "label_categories")
public class LabelCategoryEntity {
    @Id
    private Long id;
    private String category;
}
