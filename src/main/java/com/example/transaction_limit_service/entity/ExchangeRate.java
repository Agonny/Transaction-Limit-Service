package com.example.transaction_limit_service.entity;

import com.example.transaction_limit_service.constant.TableName;
import com.example.transaction_limit_service.enums.CurrencyShortname;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Table(value = TableName.EXCHANGE_RATE)
public class ExchangeRate {

    @PrimaryKeyColumn(ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private CurrencyShortname base;

    @PrimaryKeyColumn(ordinal = 0)
    private CurrencyShortname target;

    @Column
    private Float value;

}
