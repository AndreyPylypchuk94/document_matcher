package com.datapath.procurementdata.documentmatcher.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabelDataDTO {
    @NotNull
    private Long id;
    private String text;

    public LabelDataDTO(Long id) {
        this.id = id;
    }
}
