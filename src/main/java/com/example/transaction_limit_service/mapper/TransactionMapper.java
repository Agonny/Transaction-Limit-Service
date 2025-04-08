package com.example.transaction_limit_service.mapper;

import com.example.transaction_limit_service.dto.TransactionCreateDto;
import com.example.transaction_limit_service.dto.TransactionDto;
import com.example.transaction_limit_service.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TransactionMapper {

    String LIMIT_SUM_FIELD = "limit_sum";
    String LIMIT_DATETIME_FIELD = "limit_datetime";
    String LIMIT_CURRENCY_SHORTNAME_FIELD = "limit_currency_shortname";

    String SUM_SOURCE_FIELD = "remainder.limit.value";
    String DATETIME_SOURCE_FIELD = "remainder.limit.record_time";
    String CURRENCY_SHORTNAME_SOURCE_EXPRESSION = "java(com.example.transaction_limit_service.enums.CurrencyShortname.USD)";

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mapping(target = LIMIT_SUM_FIELD, source = SUM_SOURCE_FIELD)
    @Mapping(target = LIMIT_DATETIME_FIELD, source = DATETIME_SOURCE_FIELD)
    @Mapping(target = LIMIT_CURRENCY_SHORTNAME_FIELD, expression = CURRENCY_SHORTNAME_SOURCE_EXPRESSION)
    TransactionDto toDto(Transaction entity);

    Transaction toEntity(TransactionCreateDto entity);

    List<TransactionDto> toListDto(List<Transaction> listOfEntities);

}
