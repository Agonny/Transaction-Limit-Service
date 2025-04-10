package com.example.transaction_limit_service.controller;

import com.example.transaction_limit_service.dto.TransactionCreateDto;
import com.example.transaction_limit_service.dto.TransactionDto;
import com.example.transaction_limit_service.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public void addNewTransaction(@RequestBody TransactionCreateDto dto) {
        transactionService.createNewTransaction(dto);
    }

    @GetMapping("/client/exceeded")
    public List<TransactionDto> getExceededTransactions() {
        return transactionService.getExceededTransactions();
    }

}
