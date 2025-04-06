package com.example.transaction_limit_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableCassandraRepositories(value = "com.example.transaction_limit_service.repository.cassandra")
public class TransactionLimitServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionLimitServiceApplication.class, args);
    }

}
