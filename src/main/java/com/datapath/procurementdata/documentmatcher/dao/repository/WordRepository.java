package com.datapath.procurementdata.documentmatcher.dao.repository;

import com.datapath.procurementdata.documentmatcher.dao.entity.WordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WordRepository extends JpaRepository<WordEntity, Long> {
    List<WordEntity> findAllByOrderByValue();
}
