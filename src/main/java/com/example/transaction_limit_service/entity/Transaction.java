package com.example.transaction_limit_service.entity;

import com.example.transaction_limit_service.constant.TableName;
import com.example.transaction_limit_service.enums.CurrencyShortname;
import com.example.transaction_limit_service.enums.ExpenseCategory;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(name = TableName.TRANSACTION)
@NamedEntityGraph(name = "transaction-full-graph", attributeNodes = @NamedAttributeNode(value = "remainder", subgraph = "limit-remainder-graph"),
        subgraphs = @NamedSubgraph(name = "limit-remainder-graph", attributeNodes = @NamedAttributeNode("limit"))
)
public class Transaction {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactionGen")
//    @SequenceGenerator(name = "transactionGen", sequenceName = "transaction_seq", allocationSize = 10)
    private Long id;

    private Long account_from;

    private Long account_to;

    @Enumerated(EnumType.STRING)
    private CurrencyShortname currency_shortname;

    @Enumerated(EnumType.STRING)
    private ExpenseCategory expense_category;

    @MapsId
    @JoinColumn(name = "id")
    @OneToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.LAZY)
    private LimitRemainder remainder;

    private Float sum;

    private Boolean limit_exceeded;

    private LocalDateTime datetime;

}
