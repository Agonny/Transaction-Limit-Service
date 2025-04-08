package com.example.transaction_limit_service.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode
public class ExchangeRateCreateDto {

    private Map<String, Float> rates;

}
