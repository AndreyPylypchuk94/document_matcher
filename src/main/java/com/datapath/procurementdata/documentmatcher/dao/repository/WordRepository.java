package com.datapath.procurementdata.documentmatcher.dao.repository;

import com.datapath.procurementdata.documentmatcher.dao.entity.DictionaryEntity;
import com.datapath.procurementdata.documentmatcher.dao.entity.WordEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WordRepository extends JpaRepository<WordEntity, Long> {
    @EntityGraph("word-entity-graph")
    List<WordEntity> findAllByDictionaryOrderByWord(DictionaryEntity entity);
}
