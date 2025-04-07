package com.example.transaction_limit_service.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuthFeignInterceptor implements RequestInterceptor {

    @Value(value = "${spring.rate-exchanges.api-key}")
    private String API_KEY;

    @Override
    public void apply(RequestTemplate template) {
        template.header("api_key", API_KEY);
    }

}
