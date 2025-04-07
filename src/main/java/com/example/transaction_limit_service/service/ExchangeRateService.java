package com.example.transaction_limit_service.service;

import com.example.transaction_limit_service.dto.ExchangeRateCreateDto;

import java.util.List;

public interface ExchangeRateService {

    void saveNewExchangeRate(List<ExchangeRateCreateDto> listOfDto);

}
