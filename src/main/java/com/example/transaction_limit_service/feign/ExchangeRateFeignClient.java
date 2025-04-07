package com.example.transaction_limit_service.feign;

import com.example.transaction_limit_service.dto.ExchangeRateCreateDto;
import com.example.transaction_limit_service.dto.ExchangeRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "${spring.rate-exchanges.host}")
public interface ExchangeRateFeignClient {

    @GetMapping("${spring.rate-exchanges.api}")
    ExchangeRateCreateDto getNewExchangeRates(ExchangeRequestDto dto);

}
