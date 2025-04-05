package com.example.transaction_limit_service.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
@Getter
@Setter
@EqualsAndHashCode
//@Table(value = TableName.EXCHANGE_NAME)
public class ExchangeRate {

}
