package com.datapath.procurementdata.documentmatcher.service;

import com.datapath.procurementdata.documentmatcher.ModelMapper;
import com.datapath.procurementdata.documentmatcher.dao.entity.LabelCaseEntity;
import com.datapath.procurementdata.documentmatcher.dao.entity.LabelEntity;
import com.datapath.procurementdata.documentmatcher.dao.entity.WordEntity;
import com.datapath.procurementdata.documentmatcher.dao.repository.LabelCategoryRepository;
import com.datapath.procurementdata.documentmatcher.dao.repository.LabelRepository;
import com.datapath.procurementdata.documentmatcher.dao.repository.WordRepository;
import com.datapath.procurementdata.documentmatcher.dto.LabelCaseDTO;
import com.datapath.procurementdata.documentmatcher.dto.LabelCategoryDTO;
import com.datapath.procurementdata.documentmatcher.dto.LabelDTO;
import com.datapath.procurementdata.documentmatcher.dto.WordDTO;
import com.datapath.procurementdata.documentmatcher.dto.request.CreateWordRequest;
import com.datapath.procurementdata.documentmatcher.dto.request.SaveLabelRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CatalogWebService {

    private final LabelRepository labelRepository;
    private final WordRepository wordRepository;
    private final LabelCategoryRepository labelCategoryRepository;
    private final ModelMapper mapper;

    @Transactional
    public List<LabelDTO> getLabels() {
        return mapper.mapLabels(labelRepository.findAllByOrderByLabel());
    }

    @Transactional
    public List<WordDTO> getWords() {
        return mapper.mapWords(wordRepository.findAllByOrderByWord());
    }

    @Transactional
    public List<WordDTO> createWord(CreateWordRequest request) {
        WordEntity entity = new WordEntity();
        entity.setWord(request.getWord());
        entity.setRegexes(request.getRegexes());
        wordRepository.save(entity);
        return mapper.mapWords(wordRepository.findAllByOrderByWord());
    }

    @Transactional
    public LabelDTO saveLabel(SaveLabelRequest request) {
        LabelEntity entity = new LabelEntity();
        entity.setId(request.getId());
        entity.setLabel(request.getLabel());
        entity.setCases(map(request.getCases()));
        return mapper.map(labelRepository.save(entity));
    }

    private List<LabelCaseEntity> map(List<LabelCaseDTO> cases) {
        return cases.stream()
                .map(c -> {
                    LabelCaseEntity entity = new LabelCaseEntity();
                    entity.setId(c.getId());
                    entity.setWords(wordRepository.findAllById(c.getWordIds()));
                    return entity;
                })
                .collect(toList());
    }

    public List<LabelCategoryDTO> getLabelCategories() {
        return mapper.mapCategories(labelCategoryRepository.findAll());
    }
}
