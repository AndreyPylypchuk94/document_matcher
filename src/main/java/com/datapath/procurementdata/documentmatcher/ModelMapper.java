package com.datapath.procurementdata.documentmatcher;

import com.datapath.procurementdata.documentmatcher.dao.entity.*;
import com.datapath.procurementdata.documentmatcher.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModelMapper {
    List<WordDTO> mapWords(List<WordEntity> entity);

    WordDTO map(WordEntity entity);

    List<CategoryDTO> mapCategories(List<CategoryEntity> entity);

    CategoryDTO map(CategoryEntity entity);

    List<ResultDTO> mapResults(List<ResultEntity> entities);

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(expression = "java(entity.getWords())", target = "wordIds")
    @Mapping(expression = "java(entity.getLabels())", target = "labelIds")
    ResultDTO map(ResultEntity entity);

    @Mapping(source = "category.id", target = "categoryId")
    LabelDTO map(LabelEntity entity);

    List<LabelDTO> mapLabels(List<LabelEntity> entities);

    @Mapping(target = "wordIds", expression = "java(entity.getWords().stream().map(WordEntity::getId).collect(java.util.stream.Collectors.toList()))")
    CaseDTO map(CaseEntity entity);

    List<CaseDTO> mapLabelCases(List<CaseEntity> entities);

    List<LabelDataDTO> mapLabelData(List<ResultLabelEntity> entities);

    @Mapping(target = "id", source = "label.id")
    LabelDataDTO map(ResultLabelEntity entity);
}
