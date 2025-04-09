package com.example.transaction_limit_service.controller;

import com.example.transaction_limit_service.constant.TagName;
import com.example.transaction_limit_service.dto.TransactionCreateDto;
import com.example.transaction_limit_service.dto.TransactionDto;
import com.example.transaction_limit_service.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transaction")
@Tag(name = "Транзакции", description = "Взаимодействие с транзакциями")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    @Tag(name = TagName.INTEGRATION)
    @Operation(summary = "Добавление транзакции", description = "Позволяет доавить транзакцию")
    public void addNewTransaction(@RequestBody TransactionCreateDto dto) {
        transactionService.createNewTransaction(dto);
    }

    @GetMapping("/exceeded")
    @Tag(name = TagName.CLIENT)
    @Operation(summary = "Получение превышающих транзакций", description = "Позволяет получить все транзакции, превышающие выставленные лимиты")
    public List<TransactionDto> getExceededTransactions() {
        return transactionService.getExceededTransactions();
    }

}
