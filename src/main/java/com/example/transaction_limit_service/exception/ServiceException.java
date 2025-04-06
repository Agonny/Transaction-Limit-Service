package com.example.transaction_limit_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public abstract class ServiceException extends Exception {

    protected HttpStatusCode statusCode;

    public ServiceException(String message) {
        super(message);
    }

}
