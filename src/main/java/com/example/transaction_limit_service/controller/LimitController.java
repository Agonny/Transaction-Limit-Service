package com.example.transaction_limit_service.controller;

import com.example.transaction_limit_service.dto.LimitCreateDto;
import com.example.transaction_limit_service.service.LimitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/limit")
public class LimitController {

    private final LimitService limitService;

    @PostMapping
    public void addNewLimit(LimitCreateDto dto) {
        limitService.addNewLimit(dto);
    }

}
