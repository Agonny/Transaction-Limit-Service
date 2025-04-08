package com.example.transaction_limit_service.repository.cassandra;

import com.example.transaction_limit_service.entity.ExchangeRate;
import com.example.transaction_limit_service.enums.CurrencyShortname;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import java.util.Optional;

public interface ExchangeRateRepository extends CassandraRepository<ExchangeRate, Long> {

    @Query(value = "SELECT * FROM exchange_rate WHERE target = 'USD' AND base = ?0")
    Optional<ExchangeRate> findExchangeRateByCurrency(CurrencyShortname shortname);

}
