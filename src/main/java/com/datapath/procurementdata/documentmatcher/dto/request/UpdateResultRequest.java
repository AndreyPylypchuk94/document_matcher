package com.datapath.procurementdata.documentmatcher.dto.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;

@Data
public class UpdateResultRequest {
    @Valid
    private List<UpdateResultDTO> updates = new ArrayList<>();

    @Data
    public static class UpdateResultDTO {
        @NotNull
        private Long id;
        private List<Long> labelIds = new ArrayList<>();
        private List<Long> caseIds = new ArrayList<>();
        private boolean trash;

        @AssertTrue(message = "skipped labelIds or caseIds for non trash data")
        public boolean isValid() {
            if (!trash) return !isEmpty(labelIds) && !isEmpty(caseIds);
            return true;
        }
    }
}
