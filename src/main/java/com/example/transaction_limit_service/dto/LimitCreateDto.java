package com.example.transaction_limit_service.dto;

import com.example.transaction_limit_service.enums.ExpenseCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Schema(description = "Сущность добавляемого лимита")
public class LimitCreateDto {

    @NotNull
    @Schema(description = "Категория расхода", allowableValues = {"service", "product"})
    private ExpenseCategory category;

    @NotNull
    @Schema(description = "Величина лимита")
    private Float value;

}

