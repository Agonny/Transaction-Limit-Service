package com.example.transaction_limit_service.mapper;

import com.example.transaction_limit_service.dto.LimitCreateDto;
import com.example.transaction_limit_service.entity.Limit;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LimitMapper {

    LimitMapper INSTANCE = Mappers.getMapper(LimitMapper.class);

    Limit toEntity(LimitCreateDto dto);

}
