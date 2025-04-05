package com.example.transaction_limit_service.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
public class ExchangeRateCreateDto {

    private String base;

    private TargetRateCreateDto rate;

    private LocalDateTime time;

}
