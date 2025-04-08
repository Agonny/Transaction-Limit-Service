package com.example.transaction_limit_service.controller;

import com.example.transaction_limit_service.dto.LimitCreateDto;
import com.example.transaction_limit_service.dto.LimitDto;
import com.example.transaction_limit_service.service.LimitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/limit/client")
public class LimitController {

    private final LimitService limitService;

    @PostMapping
    public void addNewLimit(@RequestBody LimitCreateDto dto) {
        limitService.addNewLimit(dto);
    }

    @GetMapping("/all")
    public List<LimitDto> getAllLimits() {
        return limitService.getAllLimits();
    }

}
