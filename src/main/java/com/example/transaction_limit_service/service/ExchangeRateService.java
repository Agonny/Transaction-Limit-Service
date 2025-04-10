package com.example.transaction_limit_service.service;

public interface ExchangeRateService {

    /**
     * Persist fresh exchange rates from open API
     */
    void refreshExchangeRate();

}
