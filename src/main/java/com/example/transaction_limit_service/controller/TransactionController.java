package com.example.transaction_limit_service.controller;

import com.example.transaction_limit_service.dto.TransactionDto;
import com.example.transaction_limit_service.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public void addNewTransaction(TransactionDto dto) {
        transactionService.createNewTransaction(dto);
    }

    @GetMapping("/exceeded")
    public List<TransactionDto> getExceededTransactions() {
        return transactionService.getExceededTransactions();
    }

}
