package com.datapath.procurementdata.documentmatcher.dto.request;

import com.datapath.procurementdata.documentmatcher.dto.UpdateResultDTO;
import lombok.Data;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Data
public class UpdateResultRequest {
    @Valid
    private List<UpdateResultDTO> updates = new ArrayList<>();
}
