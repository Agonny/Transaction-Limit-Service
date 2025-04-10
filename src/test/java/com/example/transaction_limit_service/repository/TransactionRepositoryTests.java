package com.example.transaction_limit_service.repository;

import com.example.transaction_limit_service.entity.Limit;
import com.example.transaction_limit_service.entity.LimitRemainder;
import com.example.transaction_limit_service.entity.Transaction;
import com.example.transaction_limit_service.enums.CurrencyShortname;
import com.example.transaction_limit_service.enums.ExpenseCategory;
import com.example.transaction_limit_service.repository.postgres.LimitRemainderRepository;
import com.example.transaction_limit_service.repository.postgres.LimitRepository;
import com.example.transaction_limit_service.repository.postgres.TransactionRepository;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;

@SpringBootTest
@DisplayName("Transaction repository tests")
@AutoConfigureEmbeddedDatabase(type = AutoConfigureEmbeddedDatabase.DatabaseType.POSTGRES)
public class TransactionRepositoryTests {

    private final Integer EXCEEDED_TRANSACTIONS_COUNT = 2;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private LimitRepository limitRepository;

    @Autowired
    private LimitRemainderRepository remainderRepository;

    @Test
    @Order(value = 1)
    public void prepareTable() {
        Limit limit1 = Limit.builder()
                .value(200F)
                .category(ExpenseCategory.service)
                .remainders(new LinkedHashSet<>()).build();
        Limit limit2 = Limit.builder()
                .value(400F)
                .category(ExpenseCategory.product)
                .remainders(new LinkedHashSet<>()).build();

        List<Limit> persistedLimits = limitRepository.saveAll(List.of(limit1, limit2));

        LimitRemainder remainder1 = LimitRemainder.builder().value(200F).build();
        persistedLimits.get(0).addRemainder(remainder1);

        LimitRemainder remainder2 = LimitRemainder.builder().value(400F).build();
        persistedLimits.get(1).addRemainder(remainder2);

        List<LimitRemainder> persistedList = remainderRepository.saveAll(List.of(remainder1, remainder2));
        persistedList.get(0).setTransaction(Transaction.builder()
                .accountFrom(12390421L)
                .accountTo(157361213L)
                .currencyShortname(CurrencyShortname.KZT)
                .sum(250F)
                .expenseCategory(ExpenseCategory.service)
                .datetime(LocalDateTime.now())
                .limitExceeded(false).build());

        persistedList.get(1).setTransaction(Transaction.builder()
                .accountFrom(12291421L)
                .accountTo(1497231213L)
                .currencyShortname(CurrencyShortname.KZT)
                .sum(200F)
                .expenseCategory(ExpenseCategory.product)
                .datetime(LocalDateTime.now())
                .limitExceeded(true).build());

        remainderRepository.saveAll(persistedList);
    }

    @Test
    @Order(value = 3)
    @Transactional
    public void TransactionRepository_findAllExceeded_getExceededTransactions() {
        LimitRemainder remainder = remainderRepository.findLastRemainderOfCategory(ExpenseCategory.service).get();
        LimitRemainder newRemainder = LimitRemainder.builder().value(250F).build();
        remainder.getLimit().addRemainder(newRemainder);

        LimitRemainder persisted = remainderRepository.save(newRemainder);
        Transaction newTransaction = Transaction.builder()
                .accountFrom(188291281L)
                .accountTo(29021213L)
                .currencyShortname(CurrencyShortname.KZT)
                .sum(150F)
                .expenseCategory(ExpenseCategory.service)
                .datetime(LocalDateTime.now())
                .limitExceeded(true).build();

        persisted.setTransaction(newTransaction);
        remainderRepository.save(persisted);

        List<Transaction> persistedList = transactionRepository.findAllExceeded();

        Assertions.assertNotNull(persistedList);
        Assertions.assertEquals(persistedList.size(), EXCEEDED_TRANSACTIONS_COUNT);

        clearTable();
    }

    public void clearTable() {
        limitRepository.deleteAll();
    }
}
