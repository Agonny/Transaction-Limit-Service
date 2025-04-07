package com.example.transaction_limit_service.service.impl;

import com.example.transaction_limit_service.dto.ExchangeRequestDto;
import com.example.transaction_limit_service.entity.ExchangeRate;
import com.example.transaction_limit_service.enums.CurrencyShortname;
import com.example.transaction_limit_service.feign.ExchangeRateFeignClient;
import com.example.transaction_limit_service.mapper.ExchangeRateMapper;
import com.example.transaction_limit_service.repository.cassandra.ExchangeRateRepository;
import com.example.transaction_limit_service.service.ExchangeRateService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateRepository cassandraRepository;

    private final ExchangeRateFeignClient client;

    private final ExchangeRateMapper mapper = ExchangeRateMapper.INSTANCE;

    @Override
    public void refreshExchangeRate() {
        List<CompletableFuture<Void>> tasks = new ArrayList<>();

        for(CurrencyShortname shortname : CurrencyShortname.values()) tasks.add(createExchangeTask(shortname));

        CompletableFuture.allOf(tasks.toArray(new CompletableFuture[0])).join();
    }

    private CompletableFuture<Void> createExchangeTask(CurrencyShortname shortname) {
        return CompletableFuture.runAsync(()-> {
            ExchangeRate rate = mapper.toEntity(client.getNewExchangeRates(new ExchangeRequestDto(shortname, CurrencyShortname.USD)));
            cassandraRepository.save(rate);
        });
    }

    @Scheduled(cron = "* * 6 * * *")
    private void everydayExchangeRefresh() {
        refreshExchangeRate();
    }

    @PostConstruct
    private void refreshOnStart() {
        refreshExchangeRate();
    }

}
