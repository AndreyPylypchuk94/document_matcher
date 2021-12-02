package com.datapath.procurementdata.documentmatcher.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
public class SaveTypeRequest {
    private Long id;

    @NotBlank
    private String type;
    private List<MatchingCaseDTO> cases = new ArrayList<>();
}
