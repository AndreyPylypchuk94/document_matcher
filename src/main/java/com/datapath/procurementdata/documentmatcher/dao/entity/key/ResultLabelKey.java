package com.datapath.procurementdata.documentmatcher.dao.entity.key;

import com.datapath.procurementdata.documentmatcher.dao.entity.LabelEntity;
import com.datapath.procurementdata.documentmatcher.dao.entity.ResultEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResultLabelKey implements Serializable {
    private ResultEntity result;
    private LabelEntity label;
}
