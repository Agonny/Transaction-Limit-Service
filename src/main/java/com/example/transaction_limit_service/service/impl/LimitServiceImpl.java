package com.example.transaction_limit_service.service.impl;

import com.example.transaction_limit_service.dto.LimitCreateDto;
import com.example.transaction_limit_service.dto.LimitDto;
import com.example.transaction_limit_service.entity.Limit;
import com.example.transaction_limit_service.entity.LimitRemainder;
import com.example.transaction_limit_service.enums.ExpenseCategory;
import com.example.transaction_limit_service.exception.NegativeLimitException;
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

import java.util.List;
import java.util.Optional;

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
        if(dto.getValue() < 0F) throw new NegativeLimitException();

        Optional<LimitRemainder> optionalRemainder = limitRemainderRepository
                .findLastRemainderOfCategory(dto.getCategory());

        optionalRemainder.ifPresent(remainder -> createLimit(dto,
                remainder.getValue() + dto.getValue() - remainder.getLimit().getValue())
        );
    }

    @Override
    public List<LimitDto> getAllLimits() {
        return limitMapper.toListDto(limitRepository.findAll());
    }

    private void createLimit(LimitCreateDto dto, Float value) {
        Limit newLimit = limitMapper.toEntity(dto);
        LimitRemainder newRemainder = new LimitRemainder();

        newLimit.addRemainder(newRemainder);
        newRemainder.setValue(value);

        limitRepository.save(newLimit);
        log.info("New limit [{}] is set for category [{}]", dto.getValue(), dto.getCategory());
    }

    @Scheduled(cron = "* * * 1 * *")
    private void monthlyLimitAddition() {
        for(ExpenseCategory category : ExpenseCategory.values()) {
            LimitRemainder oldRemainder = limitRemainderRepository
                    .findLastRemainderOfCategory(category)
                    .orElseThrow();

            createLimit(new LimitCreateDto(category, DEFAULT_MONTHLY_LIMIT), oldRemainder.getValue() + DEFAULT_MONTHLY_LIMIT);
        }
    }

    @PostConstruct
    private void checkOnStart() {
        for(ExpenseCategory category : ExpenseCategory.values()) {
            if(limitRepository.findLastLimitOfCategory(category).isEmpty())
                createLimit(new LimitCreateDto(category, DEFAULT_MONTHLY_LIMIT), DEFAULT_MONTHLY_LIMIT);
        }
    }

}