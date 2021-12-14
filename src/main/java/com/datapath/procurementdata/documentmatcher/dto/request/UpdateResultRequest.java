package com.datapath.procurementdata.documentmatcher.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;

@Data
public class UpdateResultRequest {
    @NotNull
    private Long id;
    @Valid
    private List<LabelData> labels = new ArrayList<>();
    private List<Long> caseIds = new ArrayList<>();
    private boolean trash;

    @AssertTrue(message = "skipped labelIds for non trash data")
    public boolean isValid() {
        if (!trash) return !isEmpty(labels);
        return true;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LabelData {
        @NotNull
        private Long id;
        private String text;

        public LabelData(Long id) {
            this.id = id;
        }
    }
}
