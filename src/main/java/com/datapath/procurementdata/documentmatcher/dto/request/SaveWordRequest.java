package com.datapath.procurementdata.documentmatcher.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
public class SaveWordRequest {
    private Long id;
    @NotBlank
    private String word;
    private Set<String> regexes = new HashSet<>();

    @NotNull
    private Integer categoryId;
}
