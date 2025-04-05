package com.example.transaction_limit_service.repository.postgres;

import com.example.transaction_limit_service.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
