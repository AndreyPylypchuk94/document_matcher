package com.datapath.procurementdata.documentmatcher.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "labels")
@NamedEntityGraph(name = "label-entity-graph", attributeNodes = {@NamedAttributeNode(value = "cases")})
public class LabelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String label;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private LabelCategoryEntity category;

    @JoinColumn(name = "label_id")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LabelCaseEntity> cases = new ArrayList<>();
}
