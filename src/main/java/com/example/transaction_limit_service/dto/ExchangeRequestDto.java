package com.example.transaction_limit_service.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ExchangeRequestDto {

    private String base;

    private String target;

}
