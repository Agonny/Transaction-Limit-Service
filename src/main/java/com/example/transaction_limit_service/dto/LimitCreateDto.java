package com.example.transaction_limit_service.dto;

import com.example.transaction_limit_service.enums.ExpenseCategory;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class LimitCreateDto {

    private ExpenseCategory category;

    private Float amount;

}
