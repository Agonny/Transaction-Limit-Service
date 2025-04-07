package com.example.transaction_limit_service.service.impl;

import com.example.transaction_limit_service.dto.ExchangeRateCreateDto;
import com.example.transaction_limit_service.mapper.ExchangeRateMapper;
import com.example.transaction_limit_service.repository.cassandra.ExchangeRateRepository;
import com.example.transaction_limit_service.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateRepository cassandraRepository;

    private final ExchangeRateMapper mapper = ExchangeRateMapper.INSTANCE;

    @Override
    public void saveNewExchangeRate(List<ExchangeRateCreateDto> listOfDto) {

    }

}
