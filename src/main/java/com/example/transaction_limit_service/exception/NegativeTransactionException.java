package com.example.transaction_limit_service.exception;

import com.example.transaction_limit_service.enums.ExceptionMessages;
import org.springframework.http.HttpStatus;

public class NegativeTransactionException extends ServiceException {

    public NegativeTransactionException() {
        super(ExceptionMessages.NEGATIVE_TRANSACTION.getValue());
        this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
