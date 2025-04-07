package com.example.transaction_limit_service.repository.postgres;

import com.example.transaction_limit_service.entity.Limit;
import com.example.transaction_limit_service.enums.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LimitRepository extends JpaRepository<Limit, Long> {

    @Query(value = "Select l from Limit l where l.category= :category order by l.record_time desc limit 1")
    Optional<Limit> findLastLimitOfCategory(ExpenseCategory category);

}
