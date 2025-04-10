package com.example.transaction_limit_service.entity;

import com.example.transaction_limit_service.constant.TableName;
import com.example.transaction_limit_service.enums.CurrencyShortname;
import com.example.transaction_limit_service.enums.ExpenseCategory;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TableName.TRANSACTION)
@NamedEntityGraph(name = "transaction-full-graph", attributeNodes = @NamedAttributeNode(value = "remainder", subgraph = "limit-remainder-graph"),
        subgraphs = @NamedSubgraph(name = "limit-remainder-graph", attributeNodes = @NamedAttributeNode("limit"))
)
public class Transaction {

    @Id
    private Long id;

    private Long accountFrom;

    private Long accountTo;

    @Enumerated(EnumType.STRING)
    private CurrencyShortname currency_shortname;

    @Enumerated(EnumType.STRING)
    private ExpenseCategory expenseCategory;

    @MapsId
    @JoinColumn(name = "id")
    @OneToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.LAZY)
    private LimitRemainder remainder;

    private Float sum;

    private Boolean limitExceeded;

    private LocalDateTime datetime;

}
