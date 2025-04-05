package com.example.transaction_limit_service.repository.postgres;

import com.example.transaction_limit_service.entity.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LimitRepository extends JpaRepository<Limit, Long> {
}
