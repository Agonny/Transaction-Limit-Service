package com.example.transaction_limit_service.dto;

import com.example.transaction_limit_service.enums.CurrencyShortname;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class TransactionDto extends TransactionCreateDto {

    private Float limit_sum;

    private LocalDateTime limit_datetime;

    private CurrencyShortname limit_currency_shortname;

}
