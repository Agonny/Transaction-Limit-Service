package com.example.transaction_limit_service.entity;

import com.example.transaction_limit_service.constant.TableName;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(name = TableName.LIMIT_REMAINDER)
public class LimitRemainder {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "limitRemainderGen")
    @SequenceGenerator(name = "limitRemainderGen", sequenceName = "limit_remainder_seq", allocationSize = 10)
    private Long id;

    private Float value;

    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "limit_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    private Limit limit;

    @PrimaryKeyJoinColumn
    @EqualsAndHashCode.Exclude
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.LAZY)
    private Transaction transaction;

    @CreationTimestamp
    private LocalDateTime record_time;

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
        transaction.setRemainder(this);
    }

}
