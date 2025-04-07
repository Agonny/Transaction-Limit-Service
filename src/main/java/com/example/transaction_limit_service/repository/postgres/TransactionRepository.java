package com.example.transaction_limit_service.repository.postgres;

import com.example.transaction_limit_service.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByLimit_exceededTrue();

}
