package com.datapath.procurementdata.documentmatcher.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateWordRequest {
    @NotBlank
    private String value;
    @NotBlank
    private String regex;
}
