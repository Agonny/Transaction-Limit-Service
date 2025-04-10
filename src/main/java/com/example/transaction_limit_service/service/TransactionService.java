package com.example.transaction_limit_service.service;

import com.example.transaction_limit_service.dto.TransactionCreateDto;
import com.example.transaction_limit_service.dto.TransactionDto;
import com.example.transaction_limit_service.entity.Limit;
import com.example.transaction_limit_service.entity.LimitRemainder;
import com.example.transaction_limit_service.entity.Transaction;

import java.util.List;

public interface TransactionService {

    /**
     * Persist new {@link Transaction} and update actual {@link LimitRemainder} of last {@link Limit}.
     * Also set {@link Transaction} flag {@code limitExceeded} to {@code true}, if actual {@link LimitRemainder} become negative
     * @throws com.example.transaction_limit_service.exception.NegativeTransactionException if the specified transaction sum is
     * lesser or equal to zero
     */
    void createNewTransaction(TransactionCreateDto dto);

    /**
     * Returns {@link List} of {@link TransactionDto} with {@code limitExceeded} flag = {@code true}.
     */
    List<TransactionDto> getExceededTransactions();

}
