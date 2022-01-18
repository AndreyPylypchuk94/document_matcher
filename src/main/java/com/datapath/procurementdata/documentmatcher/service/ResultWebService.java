package com.datapath.procurementdata.documentmatcher.service;

import com.datapath.procurementdata.documentmatcher.ModelMapper;
import com.datapath.procurementdata.documentmatcher.dao.entity.LabelEntity;
import com.datapath.procurementdata.documentmatcher.dao.entity.ResultEntity;
import com.datapath.procurementdata.documentmatcher.dao.entity.ResultLabelEntity;
import com.datapath.procurementdata.documentmatcher.dao.repository.LabelRepository;
import com.datapath.procurementdata.documentmatcher.dao.service.ResultDaoService;
import com.datapath.procurementdata.documentmatcher.dto.LabelDataDTO;
import com.datapath.procurementdata.documentmatcher.dto.ResultDTO;
import com.datapath.procurementdata.documentmatcher.dto.request.SaveResultBatchRequest;
import com.datapath.procurementdata.documentmatcher.dto.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class ResultWebService {

    private final ResultDaoService service;
    private final LabelRepository labelRepository;
    private final ModelMapper mapper;

    @Transactional
    public PageResponse<ResultDTO> get() {
        PageResponse<ResultDTO> response = new PageResponse<>();
        response.setData(mapper.mapResults(service.get()));
        return response;
    }

    @Transactional
    public void save(SaveResultBatchRequest request) {
        Map<Long, LabelEntity> labels = labelRepository.findAllById(
                        request.getData().stream()
                                .flatMap(d -> d.getLabels().stream())
                                .map(LabelDataDTO::getId)
                                .collect(toList())
                ).stream()
                .collect(toMap(LabelEntity::getId, identity()));

        request.getData().forEach(d -> {
            ResultEntity entity = service.findById(d.getId());

            entity.setAutoHandled(request.isAutoHandled());
            entity.setTrash(d.isTrash());
            entity.setHandleDate(LocalDateTime.now());
            entity.getSelectedCases().clear();
            entity.getSelectedLabels().clear();

            if (!d.isTrash()) {

                entity.getSelectedLabels().addAll(
                        d.getLabels().stream()
                                .map(l -> {
                                    ResultLabelEntity labelingResultLabel = new ResultLabelEntity();
                                    labelingResultLabel.setText(l.getText());
                                    labelingResultLabel.setResult(entity);
                                    labelingResultLabel.setLabel(labels.get(l.getId()));
                                    return labelingResultLabel;
                                })
                                .collect(toList())
                );

                if (!isEmpty(d.getCaseIds())) {
                    entity.getSelectedCases().addAll(
                            labels.values().stream()
                                    .flatMap(l -> l.getCases().stream())
                                    .filter(c -> d.getCaseIds().contains(c.getId()))
                                    .collect(toList())
                    );
                }
            }

            service.save(entity);
        });
    }

    @Transactional
    public PageResponse<ResultDTO> getProcessed(List<Long> labelIds, int page, int size) {
        PageResponse<ResultDTO> response = new PageResponse<>();

        Page<ResultEntity> entityPage = service.getProcessedToday(labelIds, page, size);
        response.setPage(entityPage.getNumber());
        response.setTotalPages(entityPage.getTotalPages());
        response.setTotalElements(entityPage.getTotalElements());
        response.setData(mapper.mapResults(entityPage.getContent()));

        return response;
    }
}
