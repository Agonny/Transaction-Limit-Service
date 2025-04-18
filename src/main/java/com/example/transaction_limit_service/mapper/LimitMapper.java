package com.example.transaction_limit_service.mapper;

import com.example.transaction_limit_service.dto.LimitCreateDto;
import com.example.transaction_limit_service.dto.LimitDto;
import com.example.transaction_limit_service.entity.Limit;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true))
public interface LimitMapper {

    String EXPECTED_RECORD_TIME_FIELD = "record_time";
    String EXPECTED_CURRENCY_SHORTNAME_FIELD = "currency_shortname";

    String RECORD_TIME_FIELD = "recordTime";
    String CURRENCY_SHORTNAME_SOURCE_EXPRESSION = "java(com.example.transaction_limit_service.enums.CurrencyShortname.USD)";

    LimitMapper INSTANCE = Mappers.getMapper(LimitMapper.class);

    Limit toEntity(LimitCreateDto dto);

    @Mapping(target = EXPECTED_RECORD_TIME_FIELD, source = RECORD_TIME_FIELD)
    @Mapping(target = EXPECTED_CURRENCY_SHORTNAME_FIELD, expression = CURRENCY_SHORTNAME_SOURCE_EXPRESSION)
    LimitDto toDto(Limit entity);

    List<LimitDto> toListDto(List<Limit> limitList);

}
