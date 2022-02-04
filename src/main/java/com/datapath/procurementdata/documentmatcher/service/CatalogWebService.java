package com.datapath.procurementdata.documentmatcher.service;

import com.datapath.procurementdata.documentmatcher.ModelMapper;
import com.datapath.procurementdata.documentmatcher.dao.entity.CaseEntity;
import com.datapath.procurementdata.documentmatcher.dao.entity.LabelEntity;
import com.datapath.procurementdata.documentmatcher.dao.entity.WordEntity;
import com.datapath.procurementdata.documentmatcher.dao.repository.CategoryRepository;
import com.datapath.procurementdata.documentmatcher.dao.repository.LabelRepository;
import com.datapath.procurementdata.documentmatcher.dao.repository.WordRepository;
import com.datapath.procurementdata.documentmatcher.dto.CaseDTO;
import com.datapath.procurementdata.documentmatcher.dto.CategoryDTO;
import com.datapath.procurementdata.documentmatcher.dto.LabelDTO;
import com.datapath.procurementdata.documentmatcher.dto.WordDTO;
import com.datapath.procurementdata.documentmatcher.dto.request.SaveLabelRequest;
import com.datapath.procurementdata.documentmatcher.dto.request.SaveWordRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CatalogWebService {

    private final LabelRepository labelRepository;
    private final WordRepository wordRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    @Transactional
    public List<LabelDTO> getLabels(int categoryId) {
        return mapper.mapLabels(
                labelRepository.findAllByCategoryOrderByLabel(categoryRepository.getById(categoryId))
        );
    }

    @Transactional
    public List<WordDTO> getWords() {
        return mapper.mapWords(wordRepository.findAllByOrderByWord());
    }

    @Transactional
    public WordDTO saveWord(SaveWordRequest request) {
        WordEntity entity = new WordEntity();
        entity.setId(request.getId());
        entity.setWord(request.getWord());
        entity.setRegexes(request.getRegexes());
        return mapper.map(wordRepository.save(entity));
    }

    @Transactional
    public LabelDTO saveLabel(SaveLabelRequest request) {
        LabelEntity entity = new LabelEntity();
        entity.setId(request.getId());
        entity.setLabel(request.getLabel());
        entity.setCases(map(request.getCases()));
        entity.setCategory(categoryRepository.getById(request.getCategoryId()));
        return mapper.map(labelRepository.save(entity));
    }

    private List<CaseEntity> map(List<CaseDTO> cases) {
        return cases.stream()
                .map(c -> {
                    CaseEntity entity = new CaseEntity();
                    entity.setId(c.getId());
                    entity.setWords(wordRepository.findAllById(c.getWordIds()));
                    return entity;
                })
                .collect(toList());
    }

    public List<CategoryDTO> getLabelCategories() {
        return mapper.mapCategories(categoryRepository.findAll())
                .stream()
                .sorted(comparingInt(CategoryDTO::getId))
                .toList();
    }
}
