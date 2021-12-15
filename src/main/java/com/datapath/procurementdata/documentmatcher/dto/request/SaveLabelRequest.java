package com.datapath.procurementdata.documentmatcher.dto.request;

import com.datapath.procurementdata.documentmatcher.dto.CaseDTO;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
public class SaveLabelRequest {
    private Long id;

    @NotBlank
    private String label;
    private List<CaseDTO> cases = new ArrayList<>();
}
