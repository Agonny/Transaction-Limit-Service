package com.example.transaction_limit_service.repository.postgres;

import com.example.transaction_limit_service.entity.Transaction;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "transaction-full-graph")
    @Query(value = "Select t from Transaction t where t.limitExceeded = true")
    List<Transaction> findAllExceeded();

}
