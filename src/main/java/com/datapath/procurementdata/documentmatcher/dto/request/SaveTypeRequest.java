package com.datapath.procurementdata.documentmatcher.dto.request;

import com.datapath.procurementdata.documentmatcher.dto.MatchingCaseDTO;
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
