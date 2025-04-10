package com.example.transaction_limit_service.entity;

import com.example.transaction_limit_service.constant.TableName;
import com.example.transaction_limit_service.enums.ExpenseCategory;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TableName.LIMIT)
@NamedEntityGraph(name ="limit-with-remainders", attributeNodes = @NamedAttributeNode(value = "remainders"))
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
        this.remainders.add(remainder);
        remainder.setLimit(this);
    }

}
