package com.datapath.procurementdata.documentmatcher.dao.service;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MatchingResultDaoService {

    private final JdbcTemplate template;
}
