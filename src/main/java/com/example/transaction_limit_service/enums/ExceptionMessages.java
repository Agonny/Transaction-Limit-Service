package com.example.transaction_limit_service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionMessages {

    NEGATIVE_LIMIT("Limit shouldn't be negative");

    private String value;

}
