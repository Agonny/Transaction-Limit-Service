package com.example.transaction_limit_service.entity;

import com.example.transaction_limit_service.constant.TableName;
import com.example.transaction_limit_service.enums.ExpenseCategory;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

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

    @Enumerated(EnumType.STRING)
    private ExpenseCategory category;

    @CreationTimestamp
    private LocalDateTime record_time;

    private Float value;

    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "limit", fetch = FetchType.LAZY)
    private Set<LimitRemainder> remainders = new LinkedHashSet<>();

    public void addRemainder(LimitRemainder remainder) {
        remainders.add(remainder);
        remainder.setLimit(this);
    }

}
