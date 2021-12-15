package com.datapath.procurementdata.documentmatcher.service;

import com.datapath.procurementdata.documentmatcher.dao.service.ResultDaoService;
import com.datapath.procurementdata.documentmatcher.dto.LabelDataDTO;
import com.datapath.procurementdata.documentmatcher.dto.request.SaveResultBatchRequest;
import com.datapath.procurementdata.documentmatcher.dto.request.SaveResultRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@AllArgsConstructor
public class LabelAutoHandler {

    private final ResultDaoService daoService;
    private final ResultWebService service;

    @Scheduled(fixedDelay = 1000 * 60 * 60)
    private void handle() {
        log.info("Auto handling started");
        List<SaveResultRequest> batch = daoService.getCompleted()
                .stream()
                .map(c -> {
                    SaveResultRequest request = new SaveResultRequest();
                    request.setId(c.getId());
                    request.setLabels(c.getCompletedLabels().stream().map(LabelDataDTO::new).collect(toList()));
                    return request;
                }).collect(toList());
        service.save(new SaveResultBatchRequest(batch));
        log.info("Auto handling finished");
    }
}
