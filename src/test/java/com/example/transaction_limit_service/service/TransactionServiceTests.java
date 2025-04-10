package com.example.transaction_limit_service.service;

import com.example.transaction_limit_service.dto.TransactionCreateDto;
import com.example.transaction_limit_service.dto.TransactionDto;
import com.example.transaction_limit_service.enums.CurrencyShortname;
import com.example.transaction_limit_service.enums.ExpenseCategory;
import com.example.transaction_limit_service.exception.NegativeTransactionException;
import com.example.transaction_limit_service.service.impl.TransactionServiceImpl;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.cassandra.AutoConfigureDataCassandra;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureDataCassandra
@AutoConfigureEmbeddedDatabase(type = AutoConfigureEmbeddedDatabase.DatabaseType.POSTGRES)
public class TransactionServiceTests {

    @Autowired
    private TransactionServiceImpl transactionService;

    @Test
    @Order(1)
    public void TransactionService_CreateNewTransaction_SaveTransactionOnlyWithValidData() {
        TransactionCreateDto dto = TransactionCreateDto.builder()
                .account_from(188291281L)
                .account_to(29021213L)
                .currency_shortname(CurrencyShortname.RUB)
                .sum(0F)
                .expense_category(ExpenseCategory.service)
                .datetime("2025-04-02T10:10:10.431")
                .build();

        Assertions.assertEquals(assertThrows(NegativeTransactionException.class,
                () -> transactionService.createNewTransaction(dto)).getClass(), NegativeTransactionException.class);
    }

    @Test
    @Order(2)
    public void TransactionService_CreateNewTransaction_SaveTransactionAndCreateNewLimitRemainder() {
        transactionService.createNewTransaction(TransactionCreateDto.builder()
                .account_from(188291281L)
                .account_to(29021213L)
                .currency_shortname(CurrencyShortname.RUB)
                .sum(24510.12F)
                .expense_category(ExpenseCategory.service)
                .datetime("2025-04-02T10:10:10.431")
                .build());

        Assertions.assertEquals(transactionService.getExceededTransactions().size(), 0);
    }

    @Test
    @Order(3)
    public void TransactionService_CreateNewTransaction_SetsLimitExceededFlagCorrectly() {
        transactionService.createNewTransaction(TransactionCreateDto.builder()
                .account_from(188291281L)
                .account_to(29021213L)
                .currency_shortname(CurrencyShortname.RUB)
                .sum(16984.29F)
                .expense_category(ExpenseCategory.product)
                .datetime("2025-04-02T10:10:10.431")
                .build());

        Assertions.assertEquals(transactionService.getExceededTransactions().size(), 0);

        transactionService.createNewTransaction(TransactionCreateDto.builder()
                .account_from(188291281L)
                .account_to(29021213L)
                .currency_shortname(CurrencyShortname.RUB)
                .sum(110750.1F)
                .expense_category(ExpenseCategory.product)
                .datetime("2025-04-02T10:10:10.431")
                .build());

        transactionService.createNewTransaction(TransactionCreateDto.builder()
                .account_from(188291281L)
                .account_to(29021213L)
                .currency_shortname(CurrencyShortname.RUB)
                .sum(16984.29F)
                .expense_category(ExpenseCategory.product)
                .datetime("2025-04-02T10:10:10.431")
                .build());

        List<TransactionDto> exceeded = transactionService.getExceededTransactions();
        Assertions.assertEquals(exceeded.size(), 2);
        Assertions.assertNotNull(exceeded.get(0));
        Assertions.assertNotNull(exceeded.get(1));
    }

}
