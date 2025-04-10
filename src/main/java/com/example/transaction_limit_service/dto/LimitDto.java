package com.example.transaction_limit_service.dto;

import com.example.transaction_limit_service.enums.CurrencyShortname;
import com.example.transaction_limit_service.enums.ExpenseCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Schema(description = "Сущность лимита")
public class LimitDto {

    @Schema(description = "Категория расхода", allowableValues = {"service", "product"})
    private ExpenseCategory category;

    @Schema(description = "Время создания", example = "2025-04-08 20:55:30.84")
    private LocalDateTime record_time;

    @Schema(description = "Величина лимита")
    private Float value;

    @Schema(description = "Валюта лимита", allowableValues = "USD")
    private CurrencyShortname currency_shortname;

}
