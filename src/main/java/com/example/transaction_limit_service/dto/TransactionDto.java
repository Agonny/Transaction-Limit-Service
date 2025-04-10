package com.example.transaction_limit_service.dto;

import com.example.transaction_limit_service.enums.CurrencyShortname;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Сущность транзакции")
public class TransactionDto extends TransactionCreateDto {

    @Schema(description = "Величина лимита")
    private Float limit_sum;

    @Schema(description = "Время добавления лимита", example = "2025-04-08 20:55:30.84")
    private LocalDateTime limit_datetime;

    @Schema(description = "Валюта лимита", allowableValues = "USD")
    private CurrencyShortname limit_currency_shortname;

}
