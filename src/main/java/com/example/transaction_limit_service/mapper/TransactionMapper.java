package com.example.transaction_limit_service.mapper;

import com.example.transaction_limit_service.dto.TransactionDto;
import com.example.transaction_limit_service.entity.Transaction;
import org.mapstruct.Mapper;

@Mapper
public interface TransactionMapper {

    TransactionDto toDto(Transaction entity);

}
