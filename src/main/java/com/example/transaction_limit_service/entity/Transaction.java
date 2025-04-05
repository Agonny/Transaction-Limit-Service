package com.example.transaction_limit_service.entity;

import com.example.transaction_limit_service.constant.TableName;
import com.example.transaction_limit_service.enums.CurrencyShortname;
import com.example.transaction_limit_service.enums.ExpenseCategory;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Table(name = TableName.TRANSACTION)
public class Transaction {

    @Id
    private Long id;

    private Long account_from;

    private Long account_to;

    private CurrencyShortname currency_shortname;

    private ExpenseCategory expense_category;

    @OneToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.LAZY)
    private LimitRemainder remainder;

    private Float sum;

    private Boolean limit_exceeded;

}
