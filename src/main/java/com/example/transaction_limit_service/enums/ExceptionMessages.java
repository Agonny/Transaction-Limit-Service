package com.example.transaction_limit_service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionMessages {

    NEGATIVE_TRANSACTION("Transaction sum should be a positive number"),
    NEGATIVE_LIMIT("Limit value should be a positive number"),
    UNEXPECTED_ERROR("An unexpected error occurred");

    private String value;

}
