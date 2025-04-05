package com.example.transaction_limit_service.entity;

import com.example.transaction_limit_service.enums.ExpenseCategory;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Table
@Getter
@Setter
@EqualsAndHashCode
//@Table(name = TableName.LIMIT)
public class Limit {

    @Id
    private Long id;

    private ExpenseCategory category;

    private LocalDateTime record_time;

    private Float value;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH}, mappedBy = "limit", fetch = FetchType.LAZY)
    private Set<LimitRemainder> remainders = new LinkedHashSet<>();

}
