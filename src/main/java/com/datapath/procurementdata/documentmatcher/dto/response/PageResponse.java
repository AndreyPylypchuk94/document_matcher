package com.datapath.procurementdata.documentmatcher.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class PageResponse<T> {
    private List<T> data;
    private Integer totalPages;
    private Long totalElements;
    private Integer page;
}
