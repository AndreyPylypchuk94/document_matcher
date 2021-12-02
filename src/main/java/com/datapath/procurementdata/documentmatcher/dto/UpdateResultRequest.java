package com.datapath.procurementdata.documentmatcher.dto;

import lombok.Data;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Data
public class UpdateResultRequest {
    @Valid
    private List<UpdateResultDTO> updates = new ArrayList<>();
}
