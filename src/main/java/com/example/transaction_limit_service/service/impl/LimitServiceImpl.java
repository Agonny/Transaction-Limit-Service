package com.example.transaction_limit_service.service.impl;

import com.example.transaction_limit_service.dto.LimitCreateDto;
import com.example.transaction_limit_service.entity.Limit;
import com.example.transaction_limit_service.entity.LimitRemainder;
import com.example.transaction_limit_service.exception.NegativeLimitException;
import com.example.transaction_limit_service.mapper.LimitMapper;
import com.example.transaction_limit_service.repository.postgres.LimitRemainderRepository;
import com.example.transaction_limit_service.repository.postgres.LimitRepository;
import com.example.transaction_limit_service.service.LimitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class LimitServiceImpl implements LimitService {

    private final LimitRepository limitRepository;

    private final LimitRemainderRepository limitRemainderRepository;

    private final LimitMapper limitMapper = LimitMapper.INSTANCE;

    @Override
    public void createNewLimit(LimitCreateDto dto) throws NegativeLimitException {
        if(dto.getAmount() == null || dto.getAmount() < 0F) throw new NegativeLimitException();

        Optional<LimitRemainder> optionalRemainder = limitRemainderRepository
                .findLastRemainderOfCategory(dto.getCategory());

        optionalRemainder.ifPresent(remainder -> {
            Limit newLimit = limitMapper.toEntity(dto);
            LimitRemainder newRemainder = new LimitRemainder();

            newRemainder.setValue(dto.getAmount() + remainder.getValue());
            newLimit.setRemainders(Set.of(newRemainder));

            limitRepository.save(newLimit);
            log.info("Новый лимит [{}] установлен для категории [{}]",
                    dto.getAmount(), dto.getCategory());
        });

    }

}