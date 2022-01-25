package com.datapath.procurementdata.documentmatcher.dao.repository;

import com.datapath.procurementdata.documentmatcher.dao.entity.CategoryEntity;
import com.datapath.procurementdata.documentmatcher.dao.entity.LabelEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabelRepository extends JpaRepository<LabelEntity, Long> {
    @EntityGraph("label-entity-graph")
    List<LabelEntity> findAllByCategoryOrderByLabel(CategoryEntity category);
}
