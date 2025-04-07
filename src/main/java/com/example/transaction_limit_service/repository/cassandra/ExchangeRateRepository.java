package com.example.transaction_limit_service.repository.cassandra;

import com.example.transaction_limit_service.entity.ExchangeRate;
import com.example.transaction_limit_service.enums.CurrencyShortname;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.Optional;

public interface ExchangeRateRepository extends CassandraRepository<ExchangeRate, Long> {

    Optional<ExchangeRate> findByTarget_UsdAndBase(CurrencyShortname shortname);

}
