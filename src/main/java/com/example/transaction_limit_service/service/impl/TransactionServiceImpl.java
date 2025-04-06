package com.example.transaction_limit_service.service.impl;

import com.example.transaction_limit_service.dto.TransactionCreateDto;
import com.example.transaction_limit_service.dto.TransactionDto;
import com.example.transaction_limit_service.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Override
    public void createNewTransaction(TransactionCreateDto dto) {

    }

    @Override
    public List<TransactionDto> getExceededTransactions() {
        return null;
    }

}
