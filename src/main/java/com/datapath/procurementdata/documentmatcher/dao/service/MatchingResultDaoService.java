package com.datapath.procurementdata.documentmatcher.dao.service;

import com.datapath.procurementdata.documentmatcher.dao.domain.MatchingResult;
import com.datapath.procurementdata.documentmatcher.dto.UpdateResultDTO;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.time.LocalDateTime.now;

@Service
@AllArgsConstructor
public class MatchingResultDaoService {

    private static final String GET_QUERY = """
            select id, title, 
                array_to_string(words, ', ') AS words, 
                array_to_string(types, ', ') AS types
            from matching_results
            where manual_handle_date is null
            order by title
            limit 10;
            """;

    private static final String SET_TYPE_QUERY = """
            update matching_results
            set type_id            = ?,
                manual_handle_date = ?,
                trash              = false
            where id = ?;
            """;

    private static final String SET_TRASH_QUERY = """
            update matching_results
            set trash              = true,
                manual_handle_date = ?
            where id = ?;
            """;

    private final JdbcTemplate template;

    public List<MatchingResult> get() {
        return template.query(GET_QUERY, new BeanPropertyRowMapper<>(MatchingResult.class));
    }

    public void update(UpdateResultDTO updateDTO) {
        if (updateDTO.isTrash())
            template.update(SET_TRASH_QUERY, now(), updateDTO.getId());
        else
            template.update(SET_TYPE_QUERY, updateDTO.getTypeId(), now(), updateDTO.getId());
    }
}
