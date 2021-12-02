package com.datapath.procurementdata.documentmatcher.service;

import com.datapath.procurementdata.documentmatcher.dao.entity.DocumentTypeEntity;
import com.datapath.procurementdata.documentmatcher.dao.entity.MatchingCaseEntity;
import com.datapath.procurementdata.documentmatcher.dao.entity.WordEntity;
import com.datapath.procurementdata.documentmatcher.dao.repository.DocumentTypeRepository;
import com.datapath.procurementdata.documentmatcher.dao.repository.WordRepository;
import com.datapath.procurementdata.documentmatcher.dto.CreateWordRequest;
import com.datapath.procurementdata.documentmatcher.dto.MatchingCaseDTO;
import com.datapath.procurementdata.documentmatcher.dto.SaveTypeRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CatalogWebService {

    private final DocumentTypeRepository documentTypeRepository;
    private final WordRepository wordRepository;

    public List<DocumentTypeEntity> getTypes() {
        return documentTypeRepository.findAllByOrderByType();
    }

    public List<WordEntity> getWords() {
        return wordRepository.findAllByOrderByValue();
    }

    public List<WordEntity> createWord(CreateWordRequest request) {
        WordEntity entity = new WordEntity();
        entity.setValue(request.getValue());
        entity.setRegex(request.getRegex());
        wordRepository.save(entity);
        return wordRepository.findAllByOrderByValue();
    }

    @Transactional
    public DocumentTypeEntity saveType(SaveTypeRequest request) {
        DocumentTypeEntity entity = new DocumentTypeEntity();
        entity.setId(request.getId());
        entity.setType(request.getType());
        entity.setCases(map(request.getCases()));
        return documentTypeRepository.save(entity);
    }

    private List<MatchingCaseEntity> map(List<MatchingCaseDTO> cases) {
        return cases.stream()
                .map(c -> {
                    MatchingCaseEntity entity = new MatchingCaseEntity();
                    entity.setId(c.getId());
                    entity.setWords(wordRepository.findAllById(c.getWordIds()));
                    return entity;
                })
                .collect(toList());
    }
}
