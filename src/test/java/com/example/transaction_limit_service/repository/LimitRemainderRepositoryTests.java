package com.example.transaction_limit_service.repository;

import com.example.transaction_limit_service.entity.Limit;
import com.example.transaction_limit_service.entity.LimitRemainder;
import com.example.transaction_limit_service.enums.ExpenseCategory;
import com.example.transaction_limit_service.repository.postgres.LimitRemainderRepository;
import com.example.transaction_limit_service.repository.postgres.LimitRepository;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedHashSet;
import java.util.List;

@SpringBootTest
@AutoConfigureEmbeddedDatabase(type = AutoConfigureEmbeddedDatabase.DatabaseType.POSTGRES)
public class LimitRemainderRepositoryTests {

    @Autowired
    private LimitRepository limitRepository;

    @Autowired
    private LimitRemainderRepository remainderRepository;

    @Test
    @Order(1)
    public void prepareTable() {
        Limit limit1 = Limit.builder()
                .value(200F)
                .category(ExpenseCategory.service)
                .remainders(new LinkedHashSet<>()).build();
        limit1.addRemainder(LimitRemainder.builder()
                .value(200F).build());

        Limit limit2 = Limit.builder()
                .value(400F)
                .category(ExpenseCategory.product)
                .remainders(new LinkedHashSet<>()).build();
        limit2.addRemainder(LimitRemainder.builder()
                .value(400F).build());

        limitRepository.saveAll(List.of(limit1, limit2));
    }

    @Test
    @Order(2)
    public void LimitRemainderRepository_save_getRemainderWithCurrentLimit() {
        Limit limit = limitRepository.findLastLimitOfCategory(ExpenseCategory.service).get();

        LimitRemainder newRemainder = LimitRemainder.builder().value(200F).build();
        limit.addRemainder(newRemainder);
        Integer expectedSize = limit.getRemainders().size();

        LimitRemainder persisted = remainderRepository.save(newRemainder);

        Assertions.assertNotNull(persisted);

        Assertions.assertEquals(persisted.getValue(), newRemainder.getValue());
        Assertions.assertEquals(persisted.getLimit().getId(), newRemainder.getLimit().getId());
        Assertions.assertEquals(persisted.getLimit().getRemainders().size(), expectedSize);
    }

    @Test
    @Order(3)
    public void LimitRemainderRepository_findLastRemainderOfCategory_getLastRemainder() {
        Limit limit = limitRepository.findLastLimitOfCategory(ExpenseCategory.service).get();
        LimitRemainder newRemainder = LimitRemainder.builder()
                .value(300F).build();
        limit.addRemainder(newRemainder);
        Integer expectedSize = limit.getRemainders().size();

        remainderRepository.save(newRemainder);

        LimitRemainder lastRemainder = remainderRepository.findLastRemainderOfCategory(ExpenseCategory.service).get();

        Assertions.assertNotNull(lastRemainder);

        Assertions.assertEquals(lastRemainder.getValue(), newRemainder.getValue());
        Assertions.assertEquals(lastRemainder.getLimit().getId(), newRemainder.getLimit().getId());
        Assertions.assertEquals(lastRemainder.getLimit().getRemainders().size(), expectedSize);
    }

    @Test
    @Order(4)
    public void clearTable() {
        limitRepository.deleteAll();
    }

}
