package com.datapath.procurementdata.documentmatcher.dao.service;

import com.datapath.procurementdata.documentmatcher.dao.entity.ResultEntity;
import com.datapath.procurementdata.documentmatcher.dao.repository.ResultRepository;
import com.datapath.procurementdata.documentmatcher.exception.NotFoundEntityException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.domain.PageRequest.of;

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

    public Page<ResultEntity> getProcessedToday(List<Long> labelIds, int page, int size) {
        List<Long> processedToday = repository.findProcessedTodayIds(labelIds);
//        return repository.findAllById(processedToday);
        return repository.findAllByIdInOrderByValue(processedToday, of(page, size));
    }

    public void save(ResultEntity entity) {
        repository.save(entity);
    }
}
