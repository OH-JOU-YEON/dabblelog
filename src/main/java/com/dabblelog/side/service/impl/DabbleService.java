package com.dabblelog.side.service.impl;


import com.dabblelog.side.domain.Dabble;
import com.dabblelog.side.repository.DabbleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class DabbleService {

    @Autowired
    DabbleRepository dabbleRepository;

    @Scheduled(cron = "0 0 0 1 * *")
    public void makeDabble() {

        dabbleRepository.save(new Dabble(LocalDateTime.now().minusMonths(1)));

    }
}
