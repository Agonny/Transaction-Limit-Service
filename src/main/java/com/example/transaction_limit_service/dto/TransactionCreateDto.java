package com.example.transaction_limit_service.dto;

import com.example.transaction_limit_service.enums.CurrencyShortname;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
public class TransactionCreateDto {

    @NonNull
    private Long account_from;

    @NonNull
    private Long account_to;

    @NonNull
    private CurrencyShortname currency_shortname;

    @NonNull
    private Long sum;

    @NonNull
    private String expense_category;

    @NonNull
    private LocalDateTime datetime;

}
