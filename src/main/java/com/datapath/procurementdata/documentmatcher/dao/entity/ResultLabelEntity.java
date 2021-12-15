package com.datapath.procurementdata.documentmatcher.dao.entity;

import com.datapath.procurementdata.documentmatcher.dao.entity.key.ResultLabelKey;
import lombok.Data;

import javax.persistence.*;

@Data
@IdClass(ResultLabelKey.class)
@Entity(name = "results_labels")
public class ResultLabelEntity {

    @Id
    @JoinColumn(name = "result_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ResultEntity result;

    @Id
    @JoinColumn(name = "label_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private LabelEntity label;

    @Column(columnDefinition = "text")
    private String text;
}
