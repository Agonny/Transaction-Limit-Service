package com.example.transaction_limit_service.repository.postgres;

import com.example.transaction_limit_service.entity.LimitRemainder;
import com.example.transaction_limit_service.enums.ExpenseCategory;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LimitRemainderRepository extends JpaRepository<LimitRemainder, Long> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "remainder-with-full-limit")
    @Query(value = "Select lr from LimitRemainder lr right join fetch lr.limit l " +
            "where l.category= :category order by lr.recordTime desc limit 1")
    Optional<LimitRemainder> findLastRemainderOfCategory(ExpenseCategory category);

}
