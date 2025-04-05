package com.example.transaction_limit_service.service;

import com.example.transaction_limit_service.dto.TransactionCreateDto;
import com.example.transaction_limit_service.dto.TransactionDto;

import java.util.List;

public interface TransactionService {

    void createNewTransaction(TransactionCreateDto dto);

    List<TransactionDto> getExceededTransactions();

}
