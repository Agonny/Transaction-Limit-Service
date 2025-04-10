package com.example.transaction_limit_service.controller;

import com.example.transaction_limit_service.constant.TagName;
import com.example.transaction_limit_service.dto.LimitCreateDto;
import com.example.transaction_limit_service.dto.LimitDto;
import com.example.transaction_limit_service.service.LimitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/limit/client")
@Tag(name = "Лимиты", description = "Взаимодействие клиентов с лимитами")
public class LimitController {

    private final LimitService limitService;

    @PostMapping
    @Tag(name = TagName.CLIENT)
    @Operation(summary = "Установление лимита", description = "Позволяет установить новый лимит на определённую категорию операций")
    public void addNewLimit(@RequestBody @Valid LimitCreateDto dto) {
        limitService.addNewLimit(dto);
    }

    @GetMapping("/all")
    @Tag(name = TagName.CLIENT)
    @Operation(summary = "Получение лимитов", description = "Позволяет получить все лимиты на операции")
    public List<LimitDto> getAllLimits() {
        return limitService.getAllLimits();
    }

}
