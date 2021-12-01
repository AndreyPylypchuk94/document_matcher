package com.datapath.procurementdata.documentmatcher.service;

import com.datapath.procurementdata.documentmatcher.dao.entity.DocumentTypeEntity;
import com.datapath.procurementdata.documentmatcher.dao.repository.DocumentTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CatalogWebService {

    private final DocumentTypeRepository documentTypeRepository;

    public List<DocumentTypeEntity> getTypes() {
        return documentTypeRepository.findAllByOrderByType();
    }
}
