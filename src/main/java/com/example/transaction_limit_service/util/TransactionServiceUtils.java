package com.example.transaction_limit_service.util;

import com.example.transaction_limit_service.dto.TransactionCreateDto;
import com.example.transaction_limit_service.repository.cassandra.ExchangeRateRepository;

public class TransactionServiceUtils {

    public static Float exchangeTransactionSum(ExchangeRateRepository exchangeRateRepository, TransactionCreateDto dto) {
        Float multiplier = exchangeRateRepository.findExchangeRateByCurrency(dto.getCurrency_shortname())
                .orElseThrow().getValue();

        return multiplier * dto.getSum();
    }

}
