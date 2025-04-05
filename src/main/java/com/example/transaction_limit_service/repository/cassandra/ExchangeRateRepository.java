package com.example.transaction_limit_service.repository.cassandra;

import com.example.transaction_limit_service.entity.ExchangeRate;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface ExchangeRateRepository extends CassandraRepository<ExchangeRate, Long> {
}
