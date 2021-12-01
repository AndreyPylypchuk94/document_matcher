package com.datapath.procurementdata.documentmatcher.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "document_types")
public class DocumentTypeEntity {

    @Id
    private String type;

    @JoinColumn(name = "type_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<MatchingCaseEntity> cases = new ArrayList<>();
}
