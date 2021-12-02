package com.datapath.procurementdata.documentmatcher.dto;

import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import static java.util.Objects.nonNull;

@Data
public class UpdateResultDTO {
    @NotNull
    private Long id;
    private Long typeId;
    private boolean trash;

    @AssertTrue(message = "skipped typeId for non trash data")
    public boolean isValid() {
        if (!trash) return nonNull(typeId);
        return true;
    }
}
