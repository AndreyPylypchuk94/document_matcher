package com.datapath.procurementdata.documentmatcher.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "words")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"value", "regex"})})
public class WordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;
    private String regex;
}
