package com.example.transaction_limit_service.exception;

import com.example.transaction_limit_service.enums.ExceptionMessages;
import org.springframework.http.HttpStatus;

public final class NegativeLimitException extends ServiceException {

    public NegativeLimitException() {
        super(ExceptionMessages.NEGATIVE_LIMIT.getValue());
        this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
