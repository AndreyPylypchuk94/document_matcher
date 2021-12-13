package com.datapath.procurementdata.documentmatcher.service;

import com.datapath.procurementdata.documentmatcher.ModelMapper;
import com.datapath.procurementdata.documentmatcher.dao.service.LabelingResultDaoService;
import com.datapath.procurementdata.documentmatcher.dto.LabelingResultDTO;
import com.datapath.procurementdata.documentmatcher.dto.request.UpdateResultRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultWebService {

    private final LabelingResultDaoService service;
    private final ModelMapper mapper;

    public List<LabelingResultDTO> get() {
        return mapper.mapResults(service.get());
    }

    public void update(UpdateResultRequest request) {
        service.update(request);
    }
}
