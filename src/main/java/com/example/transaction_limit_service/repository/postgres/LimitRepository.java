package com.example.transaction_limit_service.repository.postgres;

import com.example.transaction_limit_service.entity.Limit;
import com.example.transaction_limit_service.enums.ExpenseCategory;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LimitRepository extends JpaRepository<Limit, Long> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "limit-with-remainders")
    @Query(value = "Select l from Limit l where l.category = :category order by l.recordTime desc limit 1")
    Optional<Limit> findLastLimitOfCategory(ExpenseCategory category);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "limit-with-remainders")
    List<Limit> findAll();

}
