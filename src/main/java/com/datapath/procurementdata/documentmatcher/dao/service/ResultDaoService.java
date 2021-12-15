package com.datapath.procurementdata.documentmatcher.dao.service;

import com.datapath.procurementdata.documentmatcher.dao.entity.ResultEntity;
import com.datapath.procurementdata.documentmatcher.dao.repository.ResultRepository;
import com.datapath.procurementdata.documentmatcher.exception.NotFoundEntityException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ResultDaoService {

    private final ResultRepository repository;

    public ResultEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException(id));
    }

    public List<ResultEntity> get() {
        return repository.findFirst10ByHandleDateIsNullAndCompletedLabelsIsNullOrderByValueAsc();
    }

    public List<ResultEntity> getCompleted() {
        return repository.findAllByHandleDateIsNullAndCompletedLabelsIsNotNull();
    }

    public List<ResultEntity> getProcessedToday(List<Long> labelIds) {
        List<Long> processedToday = repository.findProcessedTodayIds(labelIds);
        return repository.findAllById(processedToday);
    }

    public void save(ResultEntity entity) {
        repository.save(entity);
    }
}
