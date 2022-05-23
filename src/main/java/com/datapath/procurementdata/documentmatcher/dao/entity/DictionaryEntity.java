package com.datapath.procurementdata.documentmatcher.dao.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity(name = "dictionary")
public class DictionaryEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private String value;
}
