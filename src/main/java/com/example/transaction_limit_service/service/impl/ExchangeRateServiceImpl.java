package com.example.transaction_limit_service.service.impl;

import com.example.transaction_limit_service.entity.ExchangeRate;
import com.example.transaction_limit_service.enums.CurrencyShortname;
import com.example.transaction_limit_service.feign.ExchangeRateFeignClient;
import com.example.transaction_limit_service.repository.cassandra.ExchangeRateRepository;
import com.example.transaction_limit_service.service.ExchangeRateService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

    @Value(value = "${spring.open-exchanges.api-id}")
    private String API_KEY;

    private final ExchangeRateRepository cassandraRepository;

    private final ExchangeRateFeignClient client;

    @Override
    public void refreshExchangeRate() {
        Map<String, Float> rates = client.getNewExchangeRates(API_KEY).getRates();
        List<ExchangeRate> entities = new ArrayList<>();

        for(CurrencyShortname shortname : CurrencyShortname.values()) {
            Float value = rates.get(shortname.toString());

            if(value != null) {
                ExchangeRate exchangeRate = new ExchangeRate(shortname, CurrencyShortname.USD, 1F / value);
                entities.add(exchangeRate);
                log.info("- New stock data found!");
                log.info("- [{}-{}] - {} added", exchangeRate.getBase(), exchangeRate.getTarget(), exchangeRate.getValue());
            }
        }

        cassandraRepository.saveAll(entities);
    }

    @Scheduled(cron = "* * 6 * * *")
    private void everydayExchangeRefresh() {
        //log.info("Regular stock exchange data refresh started");
        refreshExchangeRate();
        //log.info("Regular stock exchange data refresh started");
    }

    @PostConstruct
    private void refreshOnStart() {
        //log.info("Bootstrap stock exchange data refresh started");
        refreshExchangeRate();
        //log.info("Bootstrap stock exchange data refresh completed");
    }

}
