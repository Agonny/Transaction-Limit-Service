package com.example.transaction_limit_service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionMessages {

    NEGATIVE_TRANSACTION("Transaction sum should be a positive number"),
    NEGATIVE_LIMIT("Limit value should be a positive number"),
    EMPTY_FIELDS("All fields shouldn't be empty");

    private String value;

}
