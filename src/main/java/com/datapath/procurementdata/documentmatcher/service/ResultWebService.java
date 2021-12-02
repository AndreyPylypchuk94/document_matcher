package com.datapath.procurementdata.documentmatcher.service;

import com.datapath.procurementdata.documentmatcher.dao.domain.MatchingResult;
import com.datapath.procurementdata.documentmatcher.dao.service.MatchingResultDaoService;
import com.datapath.procurementdata.documentmatcher.dto.UpdateResultDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ResultWebService {

    private final MatchingResultDaoService service;

    public List<MatchingResult> get() {
        return service.get();
    }

    public void update(List<UpdateResultDTO> updates) {
        updates.forEach(service::update);
    }
}
