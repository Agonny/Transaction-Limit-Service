package com.example.transaction_limit_service.entity;

import com.example.transaction_limit_service.constant.TableName;
import com.example.transaction_limit_service.enums.ExpenseCategory;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(name = TableName.LIMIT)
public class Limit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trLimitGen")
    @SequenceGenerator(name = "trLimitGen", sequenceName = "tr_limit_seq", allocationSize = 10)
    private Long id;

    @Enumerated
    private ExpenseCategory category;

    private LocalDateTime record_time;

    private Float value;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH}, mappedBy = "limit", fetch = FetchType.LAZY)
    private Set<LimitRemainder> remainders = new LinkedHashSet<>();

}
