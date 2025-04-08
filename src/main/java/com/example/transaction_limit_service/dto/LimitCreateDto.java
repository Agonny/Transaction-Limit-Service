package com.example.transaction_limit_service.dto;

import com.example.transaction_limit_service.enums.ExpenseCategory;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class LimitCreateDto {

    private ExpenseCategory category;

    private Float amount;

}
