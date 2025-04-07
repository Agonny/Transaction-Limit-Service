package com.example.transaction_limit_service.entity;

import com.example.transaction_limit_service.enums.CurrencyShortname;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
@Getter
@Setter
@EqualsAndHashCode
//@Table(value = TableName.EXCHANGE_NAME)
public class ExchangeRate {

    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private CurrencyShortname base;

    @PrimaryKeyColumn
    private CurrencyShortname target;

    @Column
    private Float value;

}
