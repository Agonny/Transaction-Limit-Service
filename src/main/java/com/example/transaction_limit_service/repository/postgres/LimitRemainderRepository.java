package com.example.transaction_limit_service.repository.postgres;

import com.example.transaction_limit_service.entity.LimitRemainder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LimitRemainderRepository extends JpaRepository<LimitRemainder, Long> {
}
