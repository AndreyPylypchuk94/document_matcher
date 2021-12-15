package com.datapath.procurementdata.documentmatcher.dao.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "categories")
public class CategoryEntity {
    @Id
    private Integer id;
    private String category;
}
