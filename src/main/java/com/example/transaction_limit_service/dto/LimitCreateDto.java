package com.example.transaction_limit_service.dto;

import com.example.transaction_limit_service.enums.ExpenseCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Schema(description = "Сущность добавляемого лимита")
public class LimitCreateDto {

    @Schema(description = "Категория расхода", allowableValues = {"service", "product"})
    private ExpenseCategory category;

    @Schema(description = "Величина лимита")
    private Float value;

}

