package com.example.transaction_limit_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraReactiveRepositoriesAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.TimeZone;

@EnableFeignClients
@EnableJpaRepositories
@SpringBootApplication(exclude = CassandraReactiveRepositoriesAutoConfiguration.class)
@EnableCassandraRepositories
public class TransactionLimitServiceApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+03:00"));
        SpringApplication.run(TransactionLimitServiceApplication.class, args);
    }

}
