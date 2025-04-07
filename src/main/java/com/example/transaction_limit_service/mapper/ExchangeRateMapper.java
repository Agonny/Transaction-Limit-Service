package com.example.transaction_limit_service.mapper;

import com.example.transaction_limit_service.dto.ExchangeRateCreateDto;
import com.example.transaction_limit_service.entity.ExchangeRate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ExchangeRateMapper {

    ExchangeRateMapper INSTANCE = Mappers.getMapper(ExchangeRateMapper.class);

    String TARGET_FIELD_NAME = "target";
    String VALUE_FIELD_NAME = "value";
    String RATE_FIELD_NAME = "rate.";

    @Mapping(target = TARGET_FIELD_NAME, source = RATE_FIELD_NAME + TARGET_FIELD_NAME)
    @Mapping(target = VALUE_FIELD_NAME, source = RATE_FIELD_NAME + VALUE_FIELD_NAME)
    ExchangeRate toEntity(ExchangeRateCreateDto dto);

//    List<ExchangeRate> toListEntities(List<ExchangeRateCreateDto> listOfDto) {
//        return
//    }

}
