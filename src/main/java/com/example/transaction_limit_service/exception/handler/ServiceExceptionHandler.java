package com.example.transaction_limit_service.exception.handler;

import com.example.transaction_limit_service.exception.ServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    ResponseEntity<Object> handleConflict(ServiceException ex, WebRequest request) {
        return super.handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), ex.getStatusCode(), request);
    }

}
