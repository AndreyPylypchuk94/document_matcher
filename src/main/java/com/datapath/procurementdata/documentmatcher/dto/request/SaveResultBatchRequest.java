package com.datapath.procurementdata.documentmatcher.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveResultBatchRequest {
    @Valid
    private List<SaveResultRequest> data = new ArrayList<>();
}
