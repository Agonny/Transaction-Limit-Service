package com.example.transaction_limit_service.service;

import com.example.transaction_limit_service.dto.LimitCreateDto;
import com.example.transaction_limit_service.dto.LimitDto;

import java.util.List;

public interface LimitService {

    void addNewLimit(LimitCreateDto dto);

    List<LimitDto> getAllLimits();

}
