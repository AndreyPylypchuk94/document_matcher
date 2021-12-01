package com.datapath.procurementdata.documentmatcher.dao.service;

import com.datapath.procurementdata.documentmatcher.dao.domain.MatchingResult;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MatchingResultDaoService {

    private static final String GET_QUERY = """
            select id, title, 
                array_to_string(words, ', ') AS words, 
                array_to_string(types, ', ') AS types
            from matching_results
            where type is null
            order by title
            limit 10;
            """;

    private final JdbcTemplate template;

    public List<MatchingResult> get() {
        return template.query(GET_QUERY, new BeanPropertyRowMapper<>(MatchingResult.class));
    }
}
