package com.datapath.procurementdata.documentmatcher.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "words")
@NamedEntityGraph(name = "word-entity-graph", attributeNodes = {@NamedAttributeNode(value = "regexes")})
public class WordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String word;

    @ElementCollection
    @CollectionTable(name = "word_regexes", joinColumns = @JoinColumn(name = "word_id"))
    @Column(name = "regex")
    private Set<String> regexes = new HashSet<>();
}
