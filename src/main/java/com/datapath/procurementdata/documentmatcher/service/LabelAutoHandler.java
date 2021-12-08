package com.datapath.procurementdata.documentmatcher.service;

import com.datapath.procurementdata.documentmatcher.dao.domain.LabelingResult;
import com.datapath.procurementdata.documentmatcher.dao.service.LabelingResultDaoService;
import com.datapath.procurementdata.documentmatcher.dto.request.UpdateResultRequest.UpdateResultDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class LabelAutoHandler {

    private final LabelingResultDaoService daoService;

    @Scheduled(fixedDelay = 1000 * 60)
    private void handle() {
        log.info("Auto handling started");
        List<LabelingResult> completed = daoService.getCompleted();
        completed.forEach(c -> {
            UpdateResultDTO update = new UpdateResultDTO();
            update.setId(c.getId());
            update.setLabelIds(c.getCompletedLabels());
            daoService.update(update);
        });
        log.info("Auto handling finished");
    }
}
