package com.example.transaction_limit_service.repository;

import com.example.transaction_limit_service.entity.Limit;
import com.example.transaction_limit_service.enums.ExpenseCategory;
import com.example.transaction_limit_service.repository.postgres.LimitRepository;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureEmbeddedDatabase(type = AutoConfigureEmbeddedDatabase.DatabaseType.POSTGRES)
public class LimitRepositoryTests {

    @Autowired
    private LimitRepository limitRepository;

    @AfterEach
    public void clearTable() {
        limitRepository.deleteAll();
    }

    @Test
    public void LimitRepository_Save_ReturnsPersistedLimit() {
        Limit limit = Limit.builder()
                .value(200F)
                .category(ExpenseCategory.product).build();

        Limit persisted = limitRepository.save(limit);

        Assertions.assertNotNull(persisted);
        Assertions.assertEquals(limit.getValue(), persisted.getValue());
        Assertions.assertEquals(limit.getCategory(), persisted.getCategory());
    }

    @Test
    public void LimitRepository_FindAll_ReturnAllLimits() {
        Limit limit1 = Limit.builder()
                .value(200F)
                .category(ExpenseCategory.product).build();
        Limit limit2 = Limit.builder()
                .value(400F)
                .category(ExpenseCategory.service).build();

        limitRepository.saveAll(List.of(limit1, limit2));

        List<Limit> persistedList = limitRepository.findAll();

        Assertions.assertNotNull(persistedList);
        Assertions.assertEquals(persistedList.size(), 2);
    }

    @Test
    public void LimitRepository_findLastLimitOfCategory_ReturnsLastLimitOfCategory() {
        Limit limit1 = Limit.builder()
                .value(200F)
                .category(ExpenseCategory.service).build();
        Limit limit2 = Limit.builder()
                .value(400F)
                .category(ExpenseCategory.product).build();
        Limit limit3 = Limit.builder()
                .value(500F)
                .category(ExpenseCategory.service).build();

        limitRepository.saveAll(List.of(limit1, limit2));
        limitRepository.save(limit3);

        Optional<Limit> optional = limitRepository.findLastLimitOfCategory(ExpenseCategory.service);
        Assertions.assertNotNull(optional);
        Limit lastLimit = optional.get();

        Assertions.assertEquals(lastLimit.getCategory(), limit3.getCategory());
        Assertions.assertEquals(lastLimit.getValue(), limit3.getValue());
    }

}
