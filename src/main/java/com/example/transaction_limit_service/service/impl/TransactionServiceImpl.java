package com.example.transaction_limit_service.service.impl;

import com.example.transaction_limit_service.dto.TransactionCreateDto;
import com.example.transaction_limit_service.dto.TransactionDto;
import com.example.transaction_limit_service.entity.Limit;
import com.example.transaction_limit_service.entity.LimitRemainder;
import com.example.transaction_limit_service.entity.Transaction;
import com.example.transaction_limit_service.exception.NegativeTransactionException;
import com.example.transaction_limit_service.mapper.TransactionMapper;
import com.example.transaction_limit_service.repository.cassandra.ExchangeRateRepository;
import com.example.transaction_limit_service.repository.postgres.LimitRemainderRepository;
import com.example.transaction_limit_service.repository.postgres.TransactionRepository;
import com.example.transaction_limit_service.service.TransactionService;
import com.example.transaction_limit_service.util.TransactionServiceUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final ExchangeRateRepository exchangeRateRepository;

    private final TransactionRepository transactionRepository;

    private final LimitRemainderRepository remainderRepository;

    private final TransactionMapper transactionMapper = TransactionMapper.INSTANCE;

    @Override
    @Transactional
    public void createNewTransaction(TransactionCreateDto dto) {
        if(dto.getSum() <= 0) throw new NegativeTransactionException();

        Transaction transaction = transactionMapper.toEntity(dto);

        LimitRemainder remainder = remainderRepository
                .findLastRemainderOfCategory(transaction.getExpenseCategory()).orElseThrow();
        LimitRemainder new_remainder = new LimitRemainder();
        Limit limit = remainder.getLimit();

        new_remainder.setValue(remainder.getValue() - TransactionServiceUtils.exchangeTransactionSum(exchangeRateRepository, dto));

        limit.addRemainder(new_remainder);
        LimitRemainder persisted = remainderRepository.save(new_remainder);

        transaction.setLimitExceeded(persisted.getValue() < 0F);
        persisted.setTransaction(transaction);

        remainderRepository.save(persisted);
    }

    @Override
    public List<TransactionDto> getExceededTransactions() {
        return transactionMapper.toListDto(transactionRepository.findAllExceeded());
    }

}
