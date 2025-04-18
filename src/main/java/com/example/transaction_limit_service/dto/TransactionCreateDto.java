package com.example.transaction_limit_service.dto;

import com.example.transaction_limit_service.enums.CurrencyShortname;
import com.example.transaction_limit_service.enums.ExpenseCategory;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Schema(description = "Сущность добавляемой транзакции")
public class TransactionCreateDto {

    @NotNull
    @Schema(description = "Банковский счёт клиента")
    private Long account_from;

    @NotNull
    @Schema(description = "Банковский счёт контрагента")
    private Long account_to;

    @NotNull
    @Schema(description = "Валюта счёта", allowableValues = {"KZT", "RUB"})
    private CurrencyShortname currency_shortname;

    @NotNull
    @Schema(description = "Сумма транзакции")
    private Float sum;

    @NotNull
    @Schema(description = "Категория расхода", allowableValues = {"product", "service"})
    private ExpenseCategory expense_category;

    @Schema(description = "Дата и время", example = "2025-04-08T20:55:30.842")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private String datetime;

}
