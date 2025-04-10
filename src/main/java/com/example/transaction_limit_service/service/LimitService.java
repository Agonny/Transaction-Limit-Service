package com.example.transaction_limit_service.service;

import com.example.transaction_limit_service.dto.LimitCreateDto;
import com.example.transaction_limit_service.dto.LimitDto;
import com.example.transaction_limit_service.entity.Limit;
import com.example.transaction_limit_service.entity.LimitRemainder;

import java.util.List;

public interface LimitService {

    /**
     * Persist new {@link Limit} with actual {@link LimitRemainder} from last {@link Limit} of current {@code category}.
     * New {@link LimitRemainder} calculation based on last {@link LimitRemainder} of same {@code category} and difference between
     * newer and last {@link Limit}.
     * @throws com.example.transaction_limit_service.exception.NegativeLimitException if the specified {@link Limit} value is negative
     */
    void addNewLimit(LimitCreateDto dto);

    /**
     * Returns {@link List} of all persisted {@link Limit}.
     */
    List<LimitDto> getAllLimits();

}
