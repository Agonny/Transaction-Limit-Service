package com.example.transaction_limit_service.dto;

import com.example.transaction_limit_service.enums.ExpenseCategory;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class LimitCreateDto {

    @NotNull
    private ExpenseCategory category;

    @NotNull
    private Float value;

}

