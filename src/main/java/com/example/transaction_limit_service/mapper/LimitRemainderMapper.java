package com.example.transaction_limit_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LimitRemainderMapper {

    LimitRemainderMapper INSTANCE = Mappers.getMapper(LimitRemainderMapper.class);

}
