package com.example.transaction_limit_service.service.impl;

import com.example.transaction_limit_service.dto.LimitCreateDto;
import com.example.transaction_limit_service.entity.Limit;
import com.example.transaction_limit_service.entity.LimitRemainder;
import com.example.transaction_limit_service.enums.ExpenseCategory;
import com.example.transaction_limit_service.mapper.LimitMapper;
import com.example.transaction_limit_service.repository.postgres.LimitRemainderRepository;
import com.example.transaction_limit_service.repository.postgres.LimitRepository;
import com.example.transaction_limit_service.service.LimitService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

@Slf4j
@Service
@RequiredArgsConstructor
public class LimitServiceImpl implements LimitService {

    private final Float DEFAULT_MONTHLY_LIMIT = 1000F;

    private final LimitRepository limitRepository;

    private final LimitRemainderRepository limitRemainderRepository;

    private final LimitMapper limitMapper = LimitMapper.INSTANCE;

    @Override
    @Transactional
    public void addNewLimit(LimitCreateDto dto) {
        //if(dto.getAmount() == null || dto.getAmount() < 0F) throw new NegativeLimitException();

        Optional<LimitRemainder> optionalRemainder = limitRemainderRepository
                .findLastRemainderOfCategory(dto.getCategory());

        optionalRemainder.ifPresent(remainder -> createLimit(
                newRemainder -> newRemainder.setValue(remainder.getValue() + DEFAULT_MONTHLY_LIMIT), dto)
        );
    }

    private void createLimit(Consumer<LimitRemainder> remainderCalculation, LimitCreateDto dto) {
        Limit newLimit = limitMapper.toEntity(dto);
        LimitRemainder newRemainder = new LimitRemainder();
        newLimit.setRemainders(Set.of(newRemainder));

        remainderCalculation.accept(newRemainder);

        limitRepository.save(newLimit);
        log.info("Новый лимит [{}] установлен для категории [{}]", dto.getAmount(), dto.getCategory());
    }

    @Scheduled(cron = "* * * 1 * *")
    private void monthlyLimitAddition() {
        for(ExpenseCategory category : ExpenseCategory.values()) {
            LimitRemainder oldRemainder = limitRemainderRepository
                    .findLastRemainderOfCategory(category)
                    .orElseThrow();

            createLimit(remainder -> remainder.setValue(oldRemainder.getValue() + DEFAULT_MONTHLY_LIMIT),
                    new LimitCreateDto(category, DEFAULT_MONTHLY_LIMIT));
        }
    }

    @PostConstruct
    private void checkOnStart() {
        for(ExpenseCategory category : ExpenseCategory.values()) {
            if(limitRepository.findLastLimitOfCategory(category).isEmpty()) createLimit(
                    remainder -> remainder.setValue(DEFAULT_MONTHLY_LIMIT),
                    new LimitCreateDto(category, DEFAULT_MONTHLY_LIMIT));
        }
    }

}