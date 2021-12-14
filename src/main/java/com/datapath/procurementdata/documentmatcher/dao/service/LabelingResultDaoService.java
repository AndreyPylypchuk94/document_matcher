package com.datapath.procurementdata.documentmatcher.dao.service;

import com.datapath.procurementdata.documentmatcher.dao.domain.LabelingResult;
import com.datapath.procurementdata.documentmatcher.dto.request.UpdateResultRequest;
import com.datapath.procurementdata.documentmatcher.dto.request.UpdateResultRequest.LabelData;
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
public class LabelingResultDaoService {

    private static final String GET_QUERY = """
            select id, value, words, labels, label_category_id as labelCategoryId
            from labeling_results
            where handle_date is null and completed_labels is null
            order by value 
            limit 10;
            """;

    private static final String GET_COMPLETED_QUERY = """
            select id, completed_labels as completedLabels
            from labeling_results
            where handle_date is null and completed_labels is not null
            order by value;
            """;

    private static final String DELETE_RESULT_LABELS_QUERY = """
            delete from labeling_results_labels where labeling_results_id = ?;
            """;

    private static final String DELETE_RESULT_CASES_QUERY = """
            delete from labeling_results_cases where labeling_results_id = ?;
            """;

    private static final String SET_LABELS_QUERY = """
            insert into labeling_results_labels values (?, ?, ?);
            """;

    private static final String SET_CASES_QUERY = """
            insert into labeling_results_cases values (?, ?);
            """;

    private static final String UPDATE_RESULT_QUERY = """
            update labeling_results
            set trash       = ?,
                handle_date = ?
            where id = ?;
            """;

    private final JdbcTemplate template;

    public List<LabelingResult> get() {
        return template.query(GET_QUERY, new BeanPropertyRowMapper<>(LabelingResult.class));
    }

    public List<LabelingResult> getCompleted() {
        return template.query(GET_COMPLETED_QUERY, new BeanPropertyRowMapper<>(LabelingResult.class));
    }

    @Transactional
    public void update(UpdateResultRequest update) {
        template.update(DELETE_RESULT_LABELS_QUERY, update.getId());
        template.update(DELETE_RESULT_CASES_QUERY, update.getId());

        if (!update.isTrash()) {
            insertLabelBatch(update.getId(), update.getLabels());
            insertCaseBatch(update.getId(), update.getCaseIds());
        }

        template.update(UPDATE_RESULT_QUERY, update.isTrash(), now(), update.getId());
    }

    private void insertCaseBatch(Long resultId, List<Long> ids) {
        template.batchUpdate(SET_CASES_QUERY, new BatchPreparedStatementSetter() {
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

    private void insertLabelBatch(Long resultId, List<LabelData> ids) {
        template.batchUpdate(SET_LABELS_QUERY, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(@NonNull PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, resultId);
                ps.setLong(2, ids.get(i).getId());
                ps.setString(3, ids.get(i).getText());
            }

            @Override
            public int getBatchSize() {
                return ids.size();
            }
        });
    }
}
