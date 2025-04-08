package com.example.transaction_limit_service.feign;

import com.example.transaction_limit_service.dto.ExchangeRateCreateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "transaction-limit-service", url = "${spring.open-exchanges.host}", path = "${spring.open-exchanges.api}")
public interface ExchangeRateFeignClient {

    @GetMapping
    ExchangeRateCreateDto getNewExchangeRates(@RequestParam(value="app_id") String id);

}
