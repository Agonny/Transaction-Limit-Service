package com.example.transaction_limit_service.dto;

import com.example.transaction_limit_service.enums.CurrencyShortname;
import com.example.transaction_limit_service.enums.ExpenseCategory;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class TransactionCreateDto {

    @NotNull
    private Long account_from;

    @NotNull
    private Long account_to;

    @NotNull
    private CurrencyShortname currency_shortname;

    @NotNull
    private Float sum;

    @NotNull
    private ExpenseCategory expense_category;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private String datetime;

}
