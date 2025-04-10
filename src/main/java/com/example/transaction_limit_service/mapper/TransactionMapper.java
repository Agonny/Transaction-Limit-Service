package com.example.transaction_limit_service.mapper;

import com.example.transaction_limit_service.dto.TransactionCreateDto;
import com.example.transaction_limit_service.dto.TransactionDto;
import com.example.transaction_limit_service.entity.Transaction;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true))
public interface TransactionMapper {

    String EXPECTED_CURRENCY_SHORTNAME_FIELD = "currency_shortname";
    String EXPECTED_ACCOUNT_FROM_FIELD = "account_from";
    String EXPECTED_ACCOUNT_TO_FIELD = "account_to";
    String EXPECTED_EXPENSE_CATEGORY_FIELD = "expense_category";

    String LIMIT_SUM_FIELD = "limit_sum";
    String LIMIT_DATETIME_FIELD = "limit_datetime";
    String LIMIT_CURRENCY_SHORTNAME_FIELD = "limit_currency_shortname";

    String ACCOUNT_FROM_FIELD = "accountFrom";
    String ACCOUNT_TO_FIELD = "accountTo";
    String EXPENSE_CATEGORY_FIELD = "expenseCategory";
    String SUM_SOURCE_FIELD = "remainder.limit.value";
    String DATETIME_SOURCE_FIELD = "remainder.limit.recordTime";
    String CURRENCY_SHORTNAME_FIELD = "currencyShortname";
    String CURRENCY_SHORTNAME_SOURCE_EXPRESSION = "java(com.example.transaction_limit_service.enums.CurrencyShortname.USD)";

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mapping(target = ACCOUNT_FROM_FIELD, source = EXPECTED_ACCOUNT_FROM_FIELD)
    @Mapping(target = ACCOUNT_TO_FIELD, source = EXPECTED_ACCOUNT_TO_FIELD)
    @Mapping(target = EXPENSE_CATEGORY_FIELD, source = EXPECTED_EXPENSE_CATEGORY_FIELD)
    @Mapping(target = CURRENCY_SHORTNAME_FIELD, source = EXPECTED_CURRENCY_SHORTNAME_FIELD)
    Transaction toEntity(TransactionCreateDto entity);

    @Mapping(target = EXPECTED_ACCOUNT_FROM_FIELD, source = ACCOUNT_FROM_FIELD)
    @Mapping(target = EXPECTED_ACCOUNT_TO_FIELD, source = ACCOUNT_TO_FIELD)
    @Mapping(target = EXPECTED_EXPENSE_CATEGORY_FIELD, source = EXPENSE_CATEGORY_FIELD)
    @Mapping(target = LIMIT_SUM_FIELD, source = SUM_SOURCE_FIELD)
    @Mapping(target = LIMIT_DATETIME_FIELD, source = DATETIME_SOURCE_FIELD)
    @Mapping(target = EXPECTED_CURRENCY_SHORTNAME_FIELD, source = CURRENCY_SHORTNAME_FIELD)
    @Mapping(target = LIMIT_CURRENCY_SHORTNAME_FIELD, expression = CURRENCY_SHORTNAME_SOURCE_EXPRESSION)
    TransactionDto toDto(Transaction entity);

    List<TransactionDto> toListDto(List<Transaction> listOfEntities);

}
