package com.example.transaction_limit_service.service;

import com.example.transaction_limit_service.dto.LimitCreateDto;

public interface LimitService {

    void createNewLimit(LimitCreateDto dto);

}
