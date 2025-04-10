package com.example.transaction_limit_service.service;

import com.example.transaction_limit_service.dto.LimitCreateDto;
import com.example.transaction_limit_service.dto.LimitDto;
import com.example.transaction_limit_service.entity.Limit;
import com.example.transaction_limit_service.enums.CurrencyShortname;
import com.example.transaction_limit_service.enums.ExpenseCategory;
import com.example.transaction_limit_service.exception.NegativeLimitException;
import com.example.transaction_limit_service.repository.postgres.LimitRepository;
import com.example.transaction_limit_service.service.impl.LimitServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LimitServiceTests {

    @Mock
    private LimitRepository limitRepository;

    @InjectMocks
    private LimitServiceImpl limitService;

    @Test
    public void LimitService_FindAll_ReturnsListOfLimitDto() {
        Limit persistedLimit1 = Limit.builder()
                .value(1000F)
                .category(ExpenseCategory.service)
                .id(1L).build();

        Limit persistedLimit2 = Limit.builder()
                .value(200F)
                .category(ExpenseCategory.product)
                .id(1L).build();

        LimitDto firstDto = LimitDto.builder().value(1000F)
                .category(ExpenseCategory.service)
                .currency_shortname(CurrencyShortname.USD).build();

        LimitDto secondDto = LimitDto.builder().value(200F)
                .category(ExpenseCategory.product)
                .currency_shortname(CurrencyShortname.USD).build();

        when(limitRepository.findAll()).thenReturn(List.of(persistedLimit1, persistedLimit2));
        List<LimitDto> result = limitService.getAllLimits();

        Assertions.assertEquals(result.get(0), firstDto);
        Assertions.assertEquals(result.get(1), secondDto);
    }

    @Test
    public void LimitService_AddNewLimit_AddLimitWhenDtoIsValid() {
        LimitCreateDto dto = LimitCreateDto.builder().value(-200F)
                .category(ExpenseCategory.product).build();

        Assertions.assertEquals(assertThrows(NegativeLimitException.class, () -> limitService.addNewLimit(dto)).getClass(), NegativeLimitException.class);
    }

}
