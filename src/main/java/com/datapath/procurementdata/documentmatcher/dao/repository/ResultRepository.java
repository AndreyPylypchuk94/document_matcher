package com.datapath.procurementdata.documentmatcher.dao.repository;

import com.datapath.procurementdata.documentmatcher.dao.entity.ResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResultRepository extends JpaRepository<ResultEntity, Long> {
    String GET_PROCESSED_TODAY_QUERY = """
            select r.*
            from results r
                     join results_labels rl on r.id = rl.result_id
            where r.handle_date::::date = now()::::date and
            (COALESCE(:ids, null) is null or rl.label_id in :ids)
            """;

    List<ResultEntity> findFirst10ByHandleDateIsNullAndCompletedLabelsIsNullOrderByValueAsc();

    List<ResultEntity> findAllByHandleDateIsNullAndCompletedLabelsIsNotNull();

    @Query(nativeQuery = true, value = GET_PROCESSED_TODAY_QUERY)
    List<Long> findProcessedTodayIds(@Param("ids") List<Long> labelIds);
}
