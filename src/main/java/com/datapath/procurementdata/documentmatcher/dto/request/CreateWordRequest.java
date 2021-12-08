package com.datapath.procurementdata.documentmatcher.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
public class CreateWordRequest {
    @NotBlank
    private String word;
    private Set<String> regexes = new HashSet<>();
}
