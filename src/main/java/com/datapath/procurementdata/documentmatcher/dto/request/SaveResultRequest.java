package com.datapath.procurementdata.documentmatcher.dto.request;

import com.datapath.procurementdata.documentmatcher.dto.LabelDataDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;

@Data
public class SaveResultRequest {
    @NotNull
    private Long id;
    @Valid
    private List<LabelDataDTO> labels = new ArrayList<>();
    private List<Long> caseIds = new ArrayList<>();
    private boolean trash;

    @JsonIgnore
    @AssertTrue(message = "skipped labels for non trash data")
    public boolean isValid() {
        if (!trash) return !isEmpty(labels);
        return true;
    }
}
