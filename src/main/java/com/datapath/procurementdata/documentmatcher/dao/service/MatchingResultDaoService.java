package com.datapath.procurementdata.documentmatcher.dao.service;

import com.datapath.procurementdata.documentmatcher.dao.domain.MatchingResult;
import com.datapath.procurementdata.documentmatcher.dto.UpdateResultDTO;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    private static final String DELETE_RESULT_TYPES_QUERY = """
            delete from matching_results_types where matching_results_id = ?;
            """;

    private static final String DELETE_RESULT_CASES_QUERY = """
            delete from matching_results_cases where matching_results_id = ?;
            """;

    private static final String SET_TYPES_QUERY = """
            insert into matching_results_types values (?, ?);
            """;

    private static final String SET_CASES_QUERY = """
            insert into matching_results_cases values (?, ?);
            """;

    private static final String UPDATE_RESULT_QUERY = """
            update matching_results
            set trash              = ?,
                manual_handle_date = ?
            where id = ?;
            """;

    private final JdbcTemplate template;

    public List<MatchingResult> get() {
        return template.query(GET_QUERY, new BeanPropertyRowMapper<>(MatchingResult.class));
    }

    @Transactional
    public void update(UpdateResultDTO updateDTO) {
        template.update(DELETE_RESULT_TYPES_QUERY, updateDTO.getId());
        template.update(DELETE_RESULT_CASES_QUERY, updateDTO.getId());

        if (!updateDTO.isTrash()) {
            insertBatch(SET_TYPES_QUERY, updateDTO.getId(), updateDTO.getTypeIds());
            insertBatch(SET_CASES_QUERY, updateDTO.getId(), updateDTO.getCaseIds());
        }

        template.update(UPDATE_RESULT_QUERY, updateDTO.isTrash(), now(), updateDTO.getId());
    }

    private void insertBatch(String sql, Long resultId, List<Long> ids) {
        template.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(@NonNull PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, resultId);
                ps.setLong(2, ids.get(i));
            }

            @Override
            public int getBatchSize() {
                return ids.size();
            }
        });
    }
}
