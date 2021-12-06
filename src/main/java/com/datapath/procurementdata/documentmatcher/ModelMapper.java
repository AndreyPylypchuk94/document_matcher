package com.datapath.procurementdata.documentmatcher;

import com.datapath.procurementdata.documentmatcher.dao.domain.LabelingResult;
import com.datapath.procurementdata.documentmatcher.dao.entity.LabelCaseEntity;
import com.datapath.procurementdata.documentmatcher.dao.entity.LabelCategoryEntity;
import com.datapath.procurementdata.documentmatcher.dao.entity.LabelEntity;
import com.datapath.procurementdata.documentmatcher.dao.entity.WordEntity;
import com.datapath.procurementdata.documentmatcher.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModelMapper {
    List<WordDTO> mapWords(List<WordEntity> entity);

    WordDTO map(WordEntity entity);

    List<LabelCategoryDTO> mapCategories(List<LabelCategoryEntity> entity);

    LabelCategoryDTO map(LabelCategoryEntity entity);

    @Mapping(source = "words", target = "wordIds")
    @Mapping(source = "labels", target = "labelIds")
    LabelingResultDTO map(LabelingResult domain);

    List<LabelingResultDTO> mapResults(List<LabelingResult> domains);

    @Mapping(source = "category.id", target = "labelCategoryId")
    LabelDTO map(LabelEntity entity);

    List<LabelDTO> mapLabels(List<LabelEntity> entities);

    @Mapping(target = "wordIds", expression = "java(entity.getWords().stream().map(WordEntity::getId).collect(java.util.stream.Collectors.toList()))")
    LabelCaseDTO map(LabelCaseEntity entity);

    List<LabelCaseDTO> mapLabelCases(List<LabelCaseEntity> entities);
}
