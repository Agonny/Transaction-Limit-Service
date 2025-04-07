package com.example.transaction_limit_service.service;

import com.example.transaction_limit_service.dto.LimitCreateDto;
import com.example.transaction_limit_service.exception.NegativeLimitException;

public interface LimitService {

    void createNewLimit(LimitCreateDto dto) throws NegativeLimitException;

}
