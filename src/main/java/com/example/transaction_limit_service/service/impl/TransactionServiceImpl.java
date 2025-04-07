package com.example.transaction_limit_service.service.impl;

import com.example.transaction_limit_service.dto.TransactionCreateDto;
import com.example.transaction_limit_service.dto.TransactionDto;
import com.example.transaction_limit_service.entity.Limit;
import com.example.transaction_limit_service.entity.LimitRemainder;
import com.example.transaction_limit_service.entity.Transaction;
import com.example.transaction_limit_service.mapper.TransactionMapper;
import com.example.transaction_limit_service.repository.cassandra.ExchangeRateRepository;
import com.example.transaction_limit_service.repository.postgres.LimitRemainderRepository;
import com.example.transaction_limit_service.repository.postgres.LimitRepository;
import com.example.transaction_limit_service.repository.postgres.TransactionRepository;
import com.example.transaction_limit_service.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final ExchangeRateRepository exchangeRateRepository;

    private final TransactionRepository transactionRepository;

    private final LimitRemainderRepository remainderRepository;

    private final LimitRepository limitRepository;

    private final TransactionMapper transactionMapper = TransactionMapper.INSTANCE;

    @Override
    public void createNewTransaction(TransactionCreateDto dto) {
        Transaction transaction = transactionMapper.toEntity(dto);

        try {
            LimitRemainder remainder = remainderRepository
                    .findLastRemainderOfCategory(transaction.getExpense_category()).orElseThrow();
            LimitRemainder newRemainder = new LimitRemainder();
            Limit limit = remainder.getLimit();

            Float exchangedSum = exchangeRateRepository.findByTarget_UsdAndBase(dto.getCurrency_shortname())
                    .orElseThrow().getValue() * transaction.getSum();

            newRemainder.setValue(remainder.getValue() - exchangedSum);
            newRemainder.setTransaction(transaction);

            if(newRemainder.getValue() < 0F) transaction.setLimit_exceeded(true);

            limit.getRemainders().add(newRemainder);
            limitRepository.save(limit);

        } catch (NoSuchElementException ex) {
            log.error("todo");
        }
    }

    @Override
    public List<TransactionDto> getExceededTransactions() {
        return transactionMapper.toListDto(transactionRepository.findAllByLimit_exceededTrue());
    }

}
